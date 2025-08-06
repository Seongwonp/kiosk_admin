package com.cafe_kiosk.kiosk_admin.dto.category;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuOptionDTO {
    private Integer optionId;
    private String optionName;
    private Integer optionPrice;
    private String optionType;
    private Integer categoryId;
    private String categoryName;
    private Boolean isDeleted;
}
