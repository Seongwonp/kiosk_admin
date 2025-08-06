//package com.cafe_kiosk.kiosk_admin.mapper;
//
//import com.cafe_kiosk.kiosk_admin.dto.MenuDTO;
//import com.cafe_kiosk.kiosk_admin.dto.sales.*;
//import com.cafe_kiosk.kiosk_admin.dto.sales.SalesSearchDTO;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@Log4j2
//@SpringBootTest
//class AdminMapperTest {
//
//    @Autowired
//    private AdminMapper adminMapper;
//
//    @Test
//    void getDailySales() {
//        SalesSearchDTO searchDTO = new SalesSearchDTO();
//        searchDTO.setStartDate("2025-07-01");
//        searchDTO.setEndDate("2025-07-25");
//
//        List<DailySalesDTO> result = adminMapper.getDailySales(searchDTO);
//        result.forEach(log::info);
//    }
//
//    @Test
//    void getMonthlySales() {
//        List<MonthlySalesDTO> result = adminMapper.getMonthlySales();
//        result.forEach(log::info);
//    }
//
//    @Test
//    void getWeeklySales() {
//        List<WeeklySalesDTO> result = adminMapper.getWeeklySales();
//        result.forEach(log::info);
//    }
//
//    @Test
//    void getHourlySales() {
//        List<HourlySalesDTO> result = adminMapper.getHourlySales();
//        result.forEach(log::info);
//    }
//
//    @Test
//    void getSoldOutProducts() {
//        List<MenuDTO> result = adminMapper.getSoldOutProducts();
//        result.forEach(log::info);
//    }
//}