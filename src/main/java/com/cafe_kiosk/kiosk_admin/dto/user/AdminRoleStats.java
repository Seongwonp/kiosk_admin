package com.cafe_kiosk.kiosk_admin.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRoleStats {
    private String adminRole;
    private int cnt;
}
