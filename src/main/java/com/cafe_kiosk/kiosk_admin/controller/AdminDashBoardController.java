package com.cafe_kiosk.kiosk_admin.controller;

import com.cafe_kiosk.kiosk_admin.domain.OrderStatus;
import com.cafe_kiosk.kiosk_admin.dto.order.Orders;
import com.cafe_kiosk.kiosk_admin.dto.sales.DailySalesDTO;
import com.cafe_kiosk.kiosk_admin.dto.sales.HourlySalesDTO;
import com.cafe_kiosk.kiosk_admin.dto.sales.MonthlySalesDTO;
import com.cafe_kiosk.kiosk_admin.dto.search.SalesSearchDTO;
import com.cafe_kiosk.kiosk_admin.service.AdminMenuService;
import com.cafe_kiosk.kiosk_admin.service.AdminOrderService;
import com.cafe_kiosk.kiosk_admin.service.AdminSalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/dashboard")
public class AdminDashBoardController {
    private final AdminSalesService adminSalesService;
    private final AdminOrderService adminOrderService;
    private final AdminMenuService adminMenuService;

    @GetMapping("/main")
    public String dashboard(SalesSearchDTO searchDTO, Model model) {
        LocalDate today = LocalDate.now();
        String todayStr = today.toString();

        SalesSearchDTO todaySearchDTO = new SalesSearchDTO();
        todaySearchDTO.setStartDate(todayStr);
        todaySearchDTO.setEndDate(todayStr);

        List<HourlySalesDTO> hourlySales = adminSalesService.getHourlySales(todaySearchDTO);
        List<DailySalesDTO> dailySales = adminSalesService.getDailySales(todaySearchDTO);
        List<MonthlySalesDTO> monthlySales = adminSalesService.getMonthlySales(new SalesSearchDTO());

        long todaySales = adminSalesService.getTodaySales();
        long monthSales = adminSalesService.getThisMonthSales();

        long yearSales = monthlySales.stream()
                .mapToLong(MonthlySalesDTO::getTotalSales)
                .sum();

        // --- 주문 상태별 주문 건수 조회
        int waitingCount = adminOrderService.findOrdersByStatus(OrderStatus.WAITING).size();
        int completeCount = adminOrderService.findOrdersByStatus(OrderStatus.COMPLETE).size();
        int cancelledCount = adminOrderService.findOrdersByStatus(OrderStatus.CANCELLED).size();

        // 진행중 주문 리스트 조회
        List<Orders> waitingOrders = adminOrderService.findOrdersByStatus(OrderStatus.WAITING);

        model.addAttribute("hourlySales", hourlySales);
        model.addAttribute("DailySales", dailySales);
        model.addAttribute("MonthlySales", monthlySales);
        model.addAttribute("todaySales", todaySales);
        model.addAttribute("monthSales", monthSales);
        model.addAttribute("yearSales", yearSales);

        model.addAttribute("waitingCount", waitingCount);
        model.addAttribute("completeCount", completeCount);
        model.addAttribute("cancelledCount", cancelledCount);

        model.addAttribute("waitingOrders", waitingOrders);

        int lowStockCount = adminMenuService.getLowStockMenuCount();
        int soldOutCount = adminMenuService.getSoldOutMenuCount();

        model.addAttribute("lowStockCount", lowStockCount);
        model.addAttribute("soldOutCount", soldOutCount);

        return "admin/dashboard/main";
    }
}
