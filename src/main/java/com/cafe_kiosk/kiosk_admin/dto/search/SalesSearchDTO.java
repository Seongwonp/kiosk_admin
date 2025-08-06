package com.cafe_kiosk.kiosk_admin.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesSearchDTO {
    private String startDate;
    private String endDate;
    private String searchType;  // 'categoryName' 또는 'menuName'
    private String keyword;     // 검색어

    private Integer page;
    private Integer limit;

    public int getOffset() {
        return (page != null && limit != null) ? (page - 1) * limit : 0;
    }
}
