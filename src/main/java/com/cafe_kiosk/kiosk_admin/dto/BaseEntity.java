package com.cafe_kiosk.kiosk_admin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class BaseEntity {
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;
}
