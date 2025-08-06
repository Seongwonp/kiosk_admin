package com.cafe_kiosk.kiosk_admin.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Integer categoryId;
    private String name;
    private String description;

}