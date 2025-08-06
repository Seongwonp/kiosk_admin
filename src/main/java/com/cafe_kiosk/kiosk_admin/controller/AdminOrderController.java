package com.cafe_kiosk.kiosk_admin.controller;

import com.cafe_kiosk.kiosk_admin.domain.OrderStatus;
import com.cafe_kiosk.kiosk_admin.dto.order.Orders;
import com.cafe_kiosk.kiosk_admin.service.AdminOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin/order")
public class AdminOrderController {
    private final AdminOrderService adminOrderService;

    @GetMapping("/waiting")
    public String waitingOrders(Model model) {
        List<Orders> orders = adminOrderService.findOrdersByStatus(OrderStatus.WAITING);
        model.addAttribute("orders", orders);
        model.addAttribute("status", OrderStatus.WAITING);
        model.addAttribute("orderCount", orders.size());
        return "/admin/order/waiting";
    }

    @GetMapping("/complete")
    public String completeOrders(Model model) {
        List<Orders> orders = adminOrderService.findOrdersByStatus(OrderStatus.COMPLETE);
        model.addAttribute("orders", orders);
        model.addAttribute("status", OrderStatus.COMPLETE);
        model.addAttribute("orderCount", orders.size());
        return "/admin/order/complete";
    }

    @GetMapping("/cancelled")
    public String cancelledOrders(Model model) {
        List<Orders> orders = adminOrderService.findOrdersByStatus(OrderStatus.CANCELLED);
        model.addAttribute("orders", orders);
        model.addAttribute("status", OrderStatus.CANCELLED);
        model.addAttribute("orderCount", orders.size());
        return "/admin/order/cancelled";
    }


    @PostMapping("/cancel")
    public String cancelOrder(@RequestParam("orderId") Integer orderId,
                              RedirectAttributes redirectAttributes) {
        try {
            adminOrderService.updateToCancelOrder(orderId);
            redirectAttributes.addFlashAttribute("message", "주문이 취소되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/order/cancelled";
    }

    @PostMapping("/complete")
    public String completeOrder(@RequestParam Integer orderId, RedirectAttributes redirectAttributes) {
        try {
            adminOrderService.updateOrderStatus(orderId, OrderStatus.COMPLETE);
            redirectAttributes.addFlashAttribute("message", "주문이 완료 상태로 변경되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/order/complete";
    }
}
