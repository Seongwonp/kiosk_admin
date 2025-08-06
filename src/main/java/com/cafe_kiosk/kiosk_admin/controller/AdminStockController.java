package com.cafe_kiosk.kiosk_admin.controller;


import com.cafe_kiosk.kiosk_admin.dto.menu.StockBatchDTO;
import com.cafe_kiosk.kiosk_admin.dto.menu.StockUpdateDTO;
import com.cafe_kiosk.kiosk_admin.service.AdminMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/stock")
public class AdminStockController {

    private final AdminMenuService adminMenuService;

    @GetMapping("/page")
    public String stockPage(){
        return "admin/stock/page";
    }


    @PostMapping("/updateBatch")
    public String updateStockBatch(StockBatchDTO dto) {
        List<StockUpdateDTO> stockList = dto.getStockList();
        adminMenuService.updateStockBatch(stockList);
        return "redirect:/admin/menu/list";
    }


    @PostMapping("/updateSoldOut")
    public String updateSoldOutStatus(@RequestParam Long menuId,
                                      @RequestParam boolean isSoldOut) {
        adminMenuService.updateSoldOut(menuId, isSoldOut); // 서비스 로직 호출
        return "redirect:/admin/menu/list"; // 목록 페이지로 리다이렉트
    }


}
