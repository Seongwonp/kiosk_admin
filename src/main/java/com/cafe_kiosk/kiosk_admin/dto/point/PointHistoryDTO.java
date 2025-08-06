package com.cafe_kiosk.kiosk_admin.dto.point;

import com.cafe_kiosk.kiosk_admin.dto.BaseEntity;
import com.cafe_kiosk.kiosk_admin.dto.order.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointHistoryDTO extends BaseEntity {
    private Integer pointHistoryId;
    private UserDTO userDTO;
    private String phone;
    private Orders order;
    private Integer amount;
    private String pointType;
}
