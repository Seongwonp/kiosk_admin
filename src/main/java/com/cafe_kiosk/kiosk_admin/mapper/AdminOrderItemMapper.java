package com.cafe_kiosk.kiosk_admin.mapper;

import com.cafe_kiosk.kiosk_admin.domain.OrderStatus;
import com.cafe_kiosk.kiosk_admin.dto.order.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminOrderItemMapper {
    void deleteOptionItemByMenuId(int menuId);
}

