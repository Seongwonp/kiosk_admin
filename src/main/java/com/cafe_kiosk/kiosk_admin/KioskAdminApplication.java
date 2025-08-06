package com.cafe_kiosk.kiosk_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class KioskAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KioskAdminApplication.class, args);
    }

}
