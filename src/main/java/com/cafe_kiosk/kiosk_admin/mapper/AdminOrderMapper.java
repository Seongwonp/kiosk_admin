package com.cafe_kiosk.kiosk_admin.mapper;

import com.cafe_kiosk.kiosk_admin.domain.OrderStatus;
import com.cafe_kiosk.kiosk_admin.dto.category.MenuOptionDTO;
import com.cafe_kiosk.kiosk_admin.dto.order.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminOrderMapper {
    List<Orders> selectAllOrdersWithItems();

    List<Orders> selectOrdersWithItemsByStatus(@Param("status") String status);

    int updateOrderStatusToCancelled(@Param("orderId") Integer orderId);

    Orders selectOrderInfoById(@Param("orderId") Integer orderId);

    void decreaseUserPoints(@Param("phone") String phone, @Param("amount") Integer amount);

    void increaseUserPoints(@Param("phone") String phone, @Param("amount") Integer amount);

    void insertPointHistory(@Param("userId") Integer userId,
                            @Param("phone") String phone,
                            @Param("orderId") Integer orderId,
                            @Param("amount") Integer amount,
                            @Param("pointType") String pointType);

    int updateOrderStatus(@Param("orderId") Integer orderId, @Param("status") OrderStatus status);
}


