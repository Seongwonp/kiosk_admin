package com.cafe_kiosk.kiosk_admin.service;

import com.cafe_kiosk.kiosk_admin.annotation.AdminLoggable;
import com.cafe_kiosk.kiosk_admin.dto.category.Category;
import com.cafe_kiosk.kiosk_admin.mapper.AdminCategoryMapper;
import com.cafe_kiosk.kiosk_admin.mapper.AdminOrderItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminOrderItemService {

    private final AdminOrderItemMapper adminOrderItemMapper;

    @AdminLoggable(actionType = "DELETE", targetTable = "orderItem", description = "삭제된 매뉴 주문정보 삭제")
    public void deleteOptionItemByMenuId(int menuId) {
        adminOrderItemMapper.deleteOptionItemByMenuId(menuId);

    }

}
