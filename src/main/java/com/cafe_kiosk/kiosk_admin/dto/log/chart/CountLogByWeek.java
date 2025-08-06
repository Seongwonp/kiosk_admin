package com.cafe_kiosk.kiosk_admin.dto.log.chart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountLogByWeek {
    private String week;
    private int logCount;
}
