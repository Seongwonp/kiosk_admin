package com.cafe_kiosk.kiosk_admin.service;

import com.cafe_kiosk.kiosk_admin.dto.sales.DailySalesDTO;
import com.cafe_kiosk.kiosk_admin.dto.search.SalesSearchDTO;
import com.cafe_kiosk.kiosk_admin.dto.sales.HourlySalesDTO;
import com.cafe_kiosk.kiosk_admin.dto.sales.MonthlySalesDTO;
import com.cafe_kiosk.kiosk_admin.dto.sales.WeeklySalesDTO;
import com.cafe_kiosk.kiosk_admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminSalesService {
    private final AdminMapper adminMapper;
    public List<DailySalesDTO> getDailySales(SalesSearchDTO searchDTO) {
        return adminMapper.getDailySales(searchDTO);
    }
    public List<MonthlySalesDTO> getMonthlySales(SalesSearchDTO searchDTO) {
        return adminMapper.getMonthlySales(searchDTO);
    }

    public List<WeeklySalesDTO> getWeeklySales(SalesSearchDTO searchDTO) {
        return adminMapper.getWeeklySales(searchDTO);
    }

    public List<HourlySalesDTO> getHourlySales(SalesSearchDTO searchDTO) {
        return adminMapper.getHourlySales(searchDTO);
    }

    public long getTodaySales() {
        return adminMapper.selectTodaySales();
    }

    public long getThisMonthSales() {
        return adminMapper.selectThisMonthSales();
    }
}
