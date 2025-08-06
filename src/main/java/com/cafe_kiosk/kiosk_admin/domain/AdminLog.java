package com.cafe_kiosk.kiosk_admin.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Entity
@Table(name = "admin_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLog{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminId", nullable = false)
    private Admin admin;

    @Column(length = 50)
    private String actionType;

    @Column(length = 50)
    private String targetTable;

    @Column(length = 50)
    private String targetId;

    private String description;

    private LocalDateTime createdAt;
}
