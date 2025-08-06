package com.cafe_kiosk.kiosk_admin.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends BaseEntity{

    @Id
    @Column(length = 20)
    private String adminId;

    @Column(length = 100)
    private String adminPw;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private AdminRole adminRole;
}
