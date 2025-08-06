package com.cafe_kiosk.kiosk_admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {

    private Integer cartId;
    private String phone;
    private MenuDTO menuDTO;
    private MenuOption option;
    private Integer quantity = 1;
}
