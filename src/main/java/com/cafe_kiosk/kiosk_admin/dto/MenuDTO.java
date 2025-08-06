package com.cafe_kiosk.kiosk_admin.dto;


import com.cafe_kiosk.kiosk_admin.dto.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private Integer menuId;
    private Category category;
    private String name;
    private Integer price;
    private String imageUrl;
    private Integer stock = 0;
    private Boolean isSoldOut = false;
}
