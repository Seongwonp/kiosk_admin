package com.cafe_kiosk.kiosk_admin.controller;

import com.cafe_kiosk.kiosk_admin.dto.point.PointHistoryDTO;
import com.cafe_kiosk.kiosk_admin.dto.point.UserDTO;
import com.cafe_kiosk.kiosk_admin.service.AdminPointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/point")
@RequiredArgsConstructor
@Log4j2
public class AdminPointController {

    private final AdminPointService adminPointService;

    @GetMapping("/list")
    public String pointList(@RequestParam(required = false) String phone,
                            Model model,
                            @RequestParam(name = "result", required = false) Boolean result) {

        List<PointHistoryDTO> histories;

        if (phone != null && !phone.isBlank()) {
            histories = adminPointService.getPointHistoriesByPhone(phone);
            UserDTO user = adminPointService.getUserByPhone(phone);

            int todayUsedPoint = adminPointService.getTodayUsedPoint(phone);
            int todaySavedPoint = adminPointService.getTodaySavedPoint(phone);

            model.addAttribute("user", user);
            model.addAttribute("phone", phone);
            model.addAttribute("todayUsedPoint", todayUsedPoint);
            model.addAttribute("todaySavedPoint", todaySavedPoint);
            model.addAttribute("todayEarnedPoint", todaySavedPoint);
        } else {
            histories = adminPointService.getAllPointHistories();
        }

        model.addAttribute("histories", histories);

        if (result != null) {
            model.addAttribute("result", result);
        }

        return "admin/point/list";
    }

    @PostMapping("/apply")
    public String applyPoint(@RequestParam String phone,
                             @RequestParam int amount,
                             @RequestParam String pointType,
                             RedirectAttributes redirectAttributes) {

        boolean result = adminPointService.updateApplyPoint(phone, amount, pointType, null);

        redirectAttributes.addFlashAttribute("result", result);

        redirectAttributes.addAttribute("phone", phone);

        return "redirect:/admin/point/list";
    }
    @PostMapping("/history/delete")
    public String deletePointHistory(@RequestParam Long pointHistoryId, RedirectAttributes redirectAttrs) {
        boolean success = adminPointService.deletePointHistory(pointHistoryId);
        redirectAttrs.addFlashAttribute("result", success);
        return "redirect:/admin/point/list";
    }
}
