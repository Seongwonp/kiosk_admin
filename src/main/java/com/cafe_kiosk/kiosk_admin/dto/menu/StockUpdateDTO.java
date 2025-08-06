package com.cafe_kiosk.kiosk_admin.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockUpdateDTO {
    private int menuId;
    private int stock;
}
