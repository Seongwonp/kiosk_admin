package com.cafe_kiosk.kiosk_admin.controller;



import com.cafe_kiosk.kiosk_admin.dto.user.Admin;
import com.cafe_kiosk.kiosk_admin.dto.user.AdminRoleStats;
import com.cafe_kiosk.kiosk_admin.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final PasswordEncoder passwordEncoder;

    @ModelAttribute("currentAdmin")
    public Admin currentAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return adminUserService.findByAdminId(auth.getName());
    }


    @PostMapping("/register")
    public String register(@ModelAttribute Admin admin) {
        admin.setAdminPw(passwordEncoder.encode(admin.getAdminPw()));
        adminUserService.insert(admin);
        return "redirect:/admin/user/list";
    }



    @GetMapping("/list")
    public String getAdminList(
            @RequestParam(required = false) String op,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String success,
            Model model
    ) {
        List<Admin> admins = adminUserService.getAdminList(op, keyword);
        List<AdminRoleStats> roleStats = adminUserService.getAdminRoleCount();
        if (roleStats == null && admins == null) {
            roleStats = new ArrayList<>();
            admins = new ArrayList<>();
        }
        admins.forEach(admin -> {
            admin.setAdminPw("");
        });
        log.info("list admin : {}", admins);
        log.info("list role : {}", roleStats);
        model.addAttribute("adminList", admins);
        model.addAttribute("roleStats", roleStats);
        model.addAttribute("success", success);
        return "admin/user/list";
    }


    @GetMapping("/modify")
    public String getModify(@RequestParam String adminId,
                            @ModelAttribute("currentAdmin") Admin currentAdmin,
                            Model model) {
        Admin targetAdmin = adminUserService.findByAdminId(adminId);

        if (targetAdmin == null) {
            throw new IllegalArgumentException("수정할 관리자가 존재하지 않습니다.");
        }

        // 권한 체크: CEO 는 모두 가능(비번, 권한), 아니면 본인만 가능
        if (!Admin.AdminRole.CEO.equals(currentAdmin.getAdminRole()) &&
                !currentAdmin.getAdminId().equals(targetAdmin.getAdminId())) {
            throw new AccessDeniedException("수정 권한이 없습니다.");
        }

        // 비밀번호는 빈 문자열 처리해서 보내기
        targetAdmin.setAdminPw("");
        model.addAttribute("targetAdmin", targetAdmin);
        model.addAttribute("currentAdmin", currentAdmin);
        return "admin/user/modify";
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute Admin admin,
                         @RequestParam(value = "currentPassword", required = false) String currentPassword,
                         @ModelAttribute("currentAdmin") Admin currentAdmin,
                         Model model) {

        // 본인 확인을 위해 비밀번호 체크 (CEO든 일반 Admin이든 공통으로)
        if (currentPassword == null || !passwordEncoder.matches(currentPassword, currentAdmin.getAdminPw())) {
            model.addAttribute("error", "현재 비밀번호가 일치하지 않습니다.");
            model.addAttribute("targetAdmin", admin);
            model.addAttribute("currentAdmin", currentAdmin);
            return "admin/user/modify";
        }

        // 비밀번호 암호화
        admin.setAdminPw(passwordEncoder.encode(admin.getAdminPw()));

        // 수정 처리
        adminUserService.modifyAdmin(currentAdmin, admin);

        return "redirect:/admin/user/list?success=true";
    }

}
