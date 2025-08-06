package com.cafe_kiosk.kiosk_admin.mapper;

import com.cafe_kiosk.kiosk_admin.dto.MenuDTO;
import com.cafe_kiosk.kiosk_admin.dto.search.SalesSearchDTO;
import com.cafe_kiosk.kiosk_admin.dto.sales.DailySalesDTO;
import com.cafe_kiosk.kiosk_admin.dto.sales.HourlySalesDTO;
import com.cafe_kiosk.kiosk_admin.dto.sales.MonthlySalesDTO;
import com.cafe_kiosk.kiosk_admin.dto.sales.WeeklySalesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AdminMapper {
    List<DailySalesDTO> getDailySales(SalesSearchDTO searchDTO); // 일별 매출 통계
    List<MonthlySalesDTO> getMonthlySales(SalesSearchDTO searchDTO); // 월별 매출 통계
    List<WeeklySalesDTO> getWeeklySales(SalesSearchDTO searchDTO); // 주간별 매출 통계
    List<HourlySalesDTO> getHourlySales(SalesSearchDTO searchDTO); // 시간별 매출 통계
    Long selectTodaySales();
    Long selectThisMonthSales();
}

