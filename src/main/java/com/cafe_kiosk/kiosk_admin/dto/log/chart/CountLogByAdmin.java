package com.cafe_kiosk.kiosk_admin.dto.log.chart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountLogByAdmin {
    private String adminId;
    private int logCount;
}
