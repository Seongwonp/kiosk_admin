package com.cafe_kiosk.kiosk_admin.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class ImageUploader {

    @Value("${ftp.server}")
    private String ftpServer;

    @Value("${ftp.port}")
    private int ftpPort;

    @Value("${ftp.user}")
    private String ftpUser;

    @Value("${ftp.pass}")
    private String ftpPass;


    private final String ftpBaseDir = "/html/img";
    private final String baseUrl = "http://"+ ftpServer + "/img/";

    // 허용되는 이미지 확장자
    private static final String[] ALLOWED_EXTENSIONS = {".jpg", ".jpeg", ".png", ".gif"};
    // 최대 파일 크기 (5MB)
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    public String upload(MultipartFile file, String categoryId) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new IllegalArgumentException("올바른 파일 이름이 아닙니다.");
        }
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        boolean validExtension = false;
        for (String allowed : ALLOWED_EXTENSIONS) {
            if (extension.equals(allowed)) {
                validExtension = true;
                break;
            }
        }
        if (!validExtension) {
            throw new IllegalArgumentException("허용되지 않는 파일 확장자입니다. (jpg, jpeg, png, gif만 허용)");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("파일 크기가 5MB를 초과합니다.");
        }

        String newFilename = UUID.randomUUID() + "_" + categoryId + extension;

        FTPClient ftpClient = new FTPClient();
        try (InputStream inputStream = file.getInputStream()) {
            ftpClient.connect(ftpServer, ftpPort);
            ftpClient.login(ftpUser, ftpPass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            String remoteDir = ftpBaseDir + "/menu/" + categoryId;
            ftpClient.makeDirectory(remoteDir);
            String remotePath = remoteDir + "/" + newFilename;

            boolean uploaded = ftpClient.storeFile(remotePath, inputStream);
            if (!uploaded) {
                throw new IOException("FTP 업로드 실패: " + ftpClient.getReplyString());
            }

            return baseUrl + "menu/" + categoryId + "/" + newFilename;

        } catch (IOException e) {
            throw new RuntimeException("FTP 파일 업로드 중 오류 발생", e);
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * 이미지의 public URL을 받아 FTP에서 해당 파일을 삭제합니다.
     * @param imageUrl public URL (예: http://swp2002.dothome.co.kr/img/menu/1/uuid_1.jpg)
     * @return 삭제 성공 여부
     */
    public boolean delete(String imageUrl) {
        if (imageUrl == null || !imageUrl.startsWith(baseUrl)) {
            throw new IllegalArgumentException("잘못된 이미지 URL입니다.");
        }
        // baseUrl을 제거하여 FTP 상대경로 생성
        String relativePath = imageUrl.substring(baseUrl.length());
        String ftpFilePath = ftpBaseDir + "/" + relativePath;

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ftpServer, ftpPort);
            ftpClient.login(ftpUser, ftpPass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            return ftpClient.deleteFile(ftpFilePath);
        } catch (IOException e) {
            throw new RuntimeException("FTP 파일 삭제 중 오류 발생", e);
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ignored) {
            }
        }
    }
}
