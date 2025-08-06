package com.cafe_kiosk.kiosk_admin.dto.menu;

import com.cafe_kiosk.kiosk_admin.dto.category.MenuOptionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminMenuDTO {
    private Integer menuId;

    private String name;
    private Integer price;
    private String imageUrl;
    private Integer stock = 0;
    private Boolean isSoldOut = false;
    private String stockStatus;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // 카테고리
    private Integer categoryId;
    private String categoryType; // 영어 타입

    // 옵션
    private List<MenuOptionDTO> optionList;
}
