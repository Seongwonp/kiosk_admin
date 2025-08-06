package com.cafe_kiosk.kiosk_admin.service;


import com.cafe_kiosk.kiosk_admin.annotation.AdminLoggable;
import com.cafe_kiosk.kiosk_admin.dto.user.Admin;
import com.cafe_kiosk.kiosk_admin.dto.user.AdminRoleStats;
import com.cafe_kiosk.kiosk_admin.mapper.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log4j2
@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserMapper adminUserMapper;

    @AdminLoggable(
        actionType = "INSERT",
        targetTable = "admin",
        description = "관리자 계정 등록"
    )
    public void insert(Admin admin) {
        adminUserMapper.insert(admin);
    }

    public Admin findByAdminId(String admin_id) {
        Admin admin = adminUserMapper.findByAdminId(admin_id);
        if(admin == null) {
            log.error("Admin not found");
            return new Admin();
        }
        admin.setAdminPw("");
        return admin;
    }
    public Admin login(String adminId){
        Admin admin = adminUserMapper.findByAdminId(adminId);
        if(admin == null) {
            log.error("Admin not found");
            return new Admin();
        }
        return admin;
    }


    public List<Admin> getAdminList(String op, String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("op", op);
        params.put("keyword", keyword);
        return adminUserMapper.adminList(params);
    }

    public List<AdminRoleStats> getAdminRoleCount() {
        return adminUserMapper.adminRoleCount();
    }

    @AdminLoggable(
        actionType = "UPDATE",
        targetTable = "admin",
        description = "관리자 정보 수정"
    )
    public void modifyAdmin(Admin currentAdmin, Admin targetAdmin) {
        if (Admin.AdminRole.CEO.equals(currentAdmin.getAdminRole())) {
            adminUserMapper.modifyByCEO(targetAdmin);
        } else if (currentAdmin.getAdminId().equals(targetAdmin.getAdminId())) {
            adminUserMapper.modifySelfPassword(targetAdmin);
        } else {
            throw new AccessDeniedException("수정 권한이 없습니다."); // 403 애러 띄우기
        }
    }
}
