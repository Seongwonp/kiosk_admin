package com.cafe_kiosk.kiosk_admin.mapper;



import com.cafe_kiosk.kiosk_admin.dto.MenuDTO;
import com.cafe_kiosk.kiosk_admin.dto.menu.AdminMenuDTO;
import com.cafe_kiosk.kiosk_admin.dto.menu.StockUpdateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface AdminMenuMapper {
    void addMenu(AdminMenuDTO menu);
    List<AdminMenuDTO> menuList(Map<String, Object> search);
    List<Map<String, Object>> getCategoryStatsAll();
    AdminMenuDTO getMenuById(int menuId);
    List<AdminMenuDTO> getSoldOutMenuList(Integer categoryId);
    List<AdminMenuDTO> getLowStockMenuList(int threshold);
    void deleteMenuById(int menuId);
    void updateMenu(AdminMenuDTO AdminMenuDTO);
    void updateStock(StockUpdateDTO stockUpdateDTO);
    void updateSoldOut(Long menuId, boolean isSoldOut);

    int totalMenuCount();
    int totalSoldOutCount();
    int totalLowStockMenuCount(int threshold);


}

