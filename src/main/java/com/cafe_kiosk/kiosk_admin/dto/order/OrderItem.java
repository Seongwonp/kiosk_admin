package com.cafe_kiosk.kiosk_admin.dto.order;

import com.cafe_kiosk.kiosk_admin.dto.MenuDTO;
import com.cafe_kiosk.kiosk_admin.dto.MenuOption;
import com.cafe_kiosk.kiosk_admin.dto.category.MenuOptionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Integer orderItemId;
    private Orders order;
    private MenuDTO menuDTO;
    private MenuOption option;
    private String options;
    private Integer quantity = 1;
    private Integer price;

    private List<MenuOptionDTO> optionList; // 옵션 ID들에 대응되는 실제 옵션 객체
}
