package com.cafe_kiosk.kiosk_admin.dto.point;

import com.cafe_kiosk.kiosk_admin.dto.BaseEntity;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseEntity {
    private Integer userId;
    private String phone;
    @Builder.Default
    private Integer points = 0;
}
