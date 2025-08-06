package com.cafe_kiosk.kiosk_admin.controller;



import com.cafe_kiosk.kiosk_admin.dto.user.Admin;
import com.cafe_kiosk.kiosk_admin.dto.user.AdminRoleStats;
import com.cafe_kiosk.kiosk_admin.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AdminMainController {

    @GetMapping("/admin/main")
    public String mainPage(Model model) {
        return "admin/main";
    }

    @GetMapping("/")
    public String goToMainPage(Model model) {
        return "redirect:/admin/main";
    }
}
