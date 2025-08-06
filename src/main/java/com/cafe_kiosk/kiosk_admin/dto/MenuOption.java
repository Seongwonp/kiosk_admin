package com.cafe_kiosk.kiosk_admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuOption {

    private Integer optionId;
    private MenuDTO menuDTO;
    private String optionName;
    private Integer optionPrice = 0;
    private String optionType;
}