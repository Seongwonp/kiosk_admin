package com.cafe_kiosk.kiosk_admin.service;

import com.cafe_kiosk.kiosk_admin.annotation.AdminLoggable;
import com.cafe_kiosk.kiosk_admin.dto.menu.AdminMenuDTO;
import com.cafe_kiosk.kiosk_admin.dto.menu.StockUpdateDTO;
import com.cafe_kiosk.kiosk_admin.mapper.AdminMenuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminMenuService {

    private final AdminMenuMapper adminMenuMapper;
    private final AdminOrderItemService adminOrderItemService;
    private final SqlSessionFactory sqlSessionFactory;

    @AdminLoggable(actionType = "INSERT", targetTable = "menu", description = "메뉴 추가")
    public void addMenu(AdminMenuDTO dto) {
        adminMenuMapper.addMenu(dto);
    }

    public List<AdminMenuDTO> getMenuList(Map<String, Object> searchParams) {
        return adminMenuMapper.menuList(searchParams);
    }

    public AdminMenuDTO getMenuById(int menuId) {
        return adminMenuMapper.getMenuById(menuId);
    }




    @AdminLoggable(actionType = "UPDATE", targetTable = "menu", description = "메뉴 수정")
    public void updateMenu(AdminMenuDTO adminMenuDTO) {
        adminMenuMapper.updateMenu(adminMenuDTO);
    }


    @AdminLoggable(actionType = "UPDATE", targetTable = "menu", description = "재고 일괄 수정")
    @Transactional
    public void updateStockBatch(List<StockUpdateDTO> stockList) {
        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            AdminMenuMapper mapper = session.getMapper(AdminMenuMapper.class);

            for (StockUpdateDTO dto : stockList) {
                mapper.updateStock(dto); // 하나씩 호출
                // 재고가 0이면 품절 처리
                boolean isSoldOut = dto.getStock() == 0;
                updateSoldOut((long) dto.getMenuId(), isSoldOut);
            }

            session.commit(); // 커밋은 한 번에!
        } catch (Exception e) {
            e.printStackTrace();
            // 실패 시 rollback도 고려 가능
        }
    }


    public List<Map<String,Object>> getCategoryStatsAll(){
        return adminMenuMapper.getCategoryStatsAll();
    }

    public List<AdminMenuDTO> getSoldOutMenuList(Integer categoryId) {
        return adminMenuMapper.getSoldOutMenuList(categoryId);
    }

    public List<AdminMenuDTO> getLowStockMenuList() {
        return adminMenuMapper.getLowStockMenuList(10);
    }

    public int getLowStockMenuCount() {
        return adminMenuMapper.totalLowStockMenuCount(10);
    }

    public int getSoldOutMenuCount() {
        return adminMenuMapper.totalSoldOutCount();
    }

    public int getTotalCount() {
        return adminMenuMapper.totalMenuCount();
    }


    @Transactional
    @AdminLoggable(actionType = "DELETE", targetTable = "menu", description = "메뉴 삭제")
    public void deleteMenuById(int menuId) {


        adminOrderItemService.deleteOptionItemByMenuId(menuId);
        adminMenuMapper.deleteMenuById(menuId);
    }

    @AdminLoggable(actionType = "UPDATE", targetTable = "menu", description = "품절 상태 변경")
    public void updateSoldOut(Long menuId, boolean isSoldOut) {
        adminMenuMapper.updateSoldOut(menuId, isSoldOut);
    }
}
