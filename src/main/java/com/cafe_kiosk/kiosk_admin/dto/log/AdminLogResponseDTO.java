package com.cafe_kiosk.kiosk_admin.dto.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminLogResponseDTO {
        private Integer logId;
        private String actionType;   // "INSERT", "UPDATE", "DELETE"
        private String targetId;    // 수정, 삭제 대상 PK
        private String targetTable;  // "menu", "category" 등
        private String targetName; // 메뉴명, 카테고리명 등
        private String description; // 변경 내용
        private String adminId;      // 관리자 ID (로그인 유저)
        private LocalDateTime createdAt;

}
