package com.cafe_kiosk.kiosk_admin.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private Integer orderId;
    private String phone;
    private LocalDateTime orderTime;
    private Integer totalAmount;
    private String status;
    private Integer usedPoint = 0;
    private Integer earnedPoint = 0;
    private String orderMethod;
    private Integer itemCount;

    private List<OrderItem> orderItemList;
}
