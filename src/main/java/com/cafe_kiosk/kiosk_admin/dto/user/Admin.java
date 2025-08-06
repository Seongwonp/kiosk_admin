package com.cafe_kiosk.kiosk_admin.dto.user;


import com.cafe_kiosk.kiosk_admin.dto.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends BaseEntity {

    private String adminId;
    private String adminPw;
    private AdminRole adminRole;
    public enum AdminRole {
        STAFF, MANAGER, CEO
    }
}
