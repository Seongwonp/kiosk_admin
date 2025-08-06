package com.cafe_kiosk.kiosk_admin.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockBatchDTO {
    private List<StockUpdateDTO> stockList;
}
