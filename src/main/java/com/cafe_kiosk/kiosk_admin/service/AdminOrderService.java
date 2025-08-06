package com.cafe_kiosk.kiosk_admin.service;

import com.cafe_kiosk.kiosk_admin.annotation.AdminLoggable;
import com.cafe_kiosk.kiosk_admin.domain.OrderStatus;
import com.cafe_kiosk.kiosk_admin.dto.MenuOption;
import com.cafe_kiosk.kiosk_admin.dto.category.MenuOptionDTO;
import com.cafe_kiosk.kiosk_admin.dto.order.OrderItem;
import com.cafe_kiosk.kiosk_admin.dto.order.Orders;
import com.cafe_kiosk.kiosk_admin.mapper.AdminOptionMapper;
import com.cafe_kiosk.kiosk_admin.mapper.AdminOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final AdminOrderMapper adminOrderMapper;
    private final AdminOptionMapper adminOptionMapper;

    @AdminLoggable(actionType = "UPDATE", targetTable = "orders", description = "주문 취소 처리")
    // 주문 취소 처리 (포인트 원복 + 포인트 이력 삭제)
    @Transactional
    public void updateToCancelOrder(Integer orderId) {
        int result = adminOrderMapper.updateOrderStatusToCancelled(orderId);
        if (result == 0) {
            throw new IllegalStateException("이미 취소된 주문이거나 존재하지 않는 주문입니다.");
        }

        Orders order = adminOrderMapper.selectOrderInfoById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("주문 정보를 찾을 수 없습니다.");
        }
        String phone = order.getPhone();
        Integer usedPoint = order.getUsedPoint();
        Integer earnedPoint = order.getEarnedPoint();

        if (usedPoint != null && usedPoint > 0) {
            adminOrderMapper.increaseUserPoints(phone, usedPoint);
            adminOrderMapper.insertPointHistory(null, phone, orderId, usedPoint, "사용 취소");
        }

        if (earnedPoint != null && earnedPoint > 0) {
            adminOrderMapper.decreaseUserPoints(phone, earnedPoint);
            adminOrderMapper.insertPointHistory(null, phone, orderId, -earnedPoint, "적립 취소");
        }
    }
    public List<Orders> findOrdersByStatus(OrderStatus status) {
        List<Orders> ordersList = adminOrderMapper.selectOrdersWithItemsByStatus(status.name());

        for (Orders order : ordersList) {
            for (OrderItem item : order.getOrderItemList()) {
                if (item.getOptions() != null && !item.getOptions().isEmpty()) {
                    // options 문자열 분리 후 Integer 리스트 변환
                    List<Integer> optionIds = Arrays.stream(item.getOptions().split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .toList();

                    List<MenuOptionDTO> options = adminOptionMapper.selectMenuOptionsByIds(optionIds);

                    item.setOptionList(options);
                }
            }
        }

        return ordersList;
    }


    @AdminLoggable(actionType = "UPDATE", targetTable = "orders", description = "주문 상태 변경")
    @Transactional
    public void updateOrderStatus(Integer orderId, OrderStatus status) {
        int result = adminOrderMapper.updateOrderStatus(orderId, status);
        if (result == 0) {
            throw new IllegalStateException("주문 상태 변경 실패: 주문이 존재하지 않거나 이미 변경된 상태입니다.");
        }
    }
}