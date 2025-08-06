package com.cafe_kiosk.kiosk_admin.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class HourlySalesDTO {
    private int hour;
    private int totalSales;
    private int orderCount;
}

