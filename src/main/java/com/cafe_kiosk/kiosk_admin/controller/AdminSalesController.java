package com.cafe_kiosk.kiosk_admin.controller;

import com.cafe_kiosk.kiosk_admin.dto.sales.*;
import com.cafe_kiosk.kiosk_admin.dto.search.SalesSearchDTO;
import com.cafe_kiosk.kiosk_admin.service.AdminSalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/sales")
@Log4j2
public class AdminSalesController {

    private final AdminSalesService adminSalesService;

    // 1. 일별 매출
    @GetMapping("/daily")
    public String getDailySales(SalesSearchDTO searchDTO, Model model) {
        if (searchDTO.getStartDate() == null || searchDTO.getStartDate().isEmpty() ||
                searchDTO.getEndDate() == null || searchDTO.getEndDate().isEmpty()) {

            LocalDate now = LocalDate.now();
            String firstDay = now.withDayOfMonth(1).toString();         // "2025-08-01"
            String lastDay = now.withDayOfMonth(now.lengthOfMonth()).toString(); // "2025-08-31"

            searchDTO.setStartDate(firstDay);
            searchDTO.setEndDate(lastDay);
        }

        log.info("검색 조건: {}", searchDTO);
        List<DailySalesDTO> dailySales = adminSalesService.getDailySales(searchDTO);
        model.addAttribute("dailySales", dailySales);
        model.addAttribute("searchDTO", searchDTO);
        return "admin/sales/daily";
    }




    // 2. 월별 매출
    @GetMapping("/monthly")
    public String getMonthlySales(SalesSearchDTO searchDTO, Model model) {
        List<MonthlySalesDTO> monthlySales = adminSalesService.getMonthlySales(searchDTO);
        model.addAttribute("monthlySales", monthlySales);
        model.addAttribute("searchDTO", searchDTO);
        return "admin/sales/monthly";
    }

    // 3. 요일별 매출
    @GetMapping("/weekly")
    public String getWeeklySales(SalesSearchDTO searchDTO, Model model) {
        List<WeeklySalesDTO> weeklySales = adminSalesService.getWeeklySales(searchDTO);
        model.addAttribute("weeklySales", weeklySales);
        model.addAttribute("searchDTO", searchDTO);
        return "admin/sales/weekly";
    }

    // 4. 시간대별 매출
    @GetMapping("/hourly")
    public String getHourlySales(SalesSearchDTO searchDTO, Model model) {
        List<HourlySalesDTO> hourlySales = adminSalesService.getHourlySales(searchDTO);
        long todaySales = adminSalesService.getTodaySales();
        model.addAttribute("totalTodaySales", todaySales);
        model.addAttribute("hourlySales", hourlySales);
        model.addAttribute("searchDTO", searchDTO);
        return "admin/sales/hourly";
    }
}
