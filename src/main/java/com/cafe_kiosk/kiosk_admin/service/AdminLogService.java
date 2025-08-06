package com.cafe_kiosk.kiosk_admin.service;

import com.cafe_kiosk.kiosk_admin.domain.User;
import com.cafe_kiosk.kiosk_admin.dto.category.Category;
import com.cafe_kiosk.kiosk_admin.dto.category.MenuOptionDTO;
import com.cafe_kiosk.kiosk_admin.dto.log.AdminLog;
import com.cafe_kiosk.kiosk_admin.dto.log.AdminLogResponseDTO;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByActionType;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByAdmin;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByMonth;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByWeek;
import com.cafe_kiosk.kiosk_admin.dto.menu.AdminMenuDTO;
import com.cafe_kiosk.kiosk_admin.dto.user.Admin;
import com.cafe_kiosk.kiosk_admin.mapper.AdminLogMapper;
import com.cafe_kiosk.kiosk_admin.mapper.AdminMenuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminLogService {
    private final AdminLogMapper adminLogMapper;
    private final AdminMenuService adminMenuService;
    private final AdminUserService adminUserService;
    private final AdminCategoryService adminCategoryService;
    private final AdminOptionService adminOptionService;

    public void insertAdminLog(AdminLog adminLog) {
        adminLogMapper.insertAdminLog(adminLog);
    }

    public List<AdminLogResponseDTO> getAllAdminLogs(Map<String,Object> params) {
        List<AdminLog> logs = adminLogMapper.getAllAdminLogs(params);

        List<AdminLogResponseDTO> list = logs.stream().map(adminLog -> {
            String targetIdStr = adminLog.getTargetId();  // 이미 String이라고 가정
            String targetName = "";

            switch (adminLog.getTargetTable()) {
                case "menu":
                    try {
                        int menuId = Integer.parseInt(targetIdStr);  // 숫자인 경우만 처리
                        AdminMenuDTO menu = adminMenuService.getMenuById(menuId);
                        targetName = (menu != null) ? menu.getName() : "";
                        log.info("메뉴아이디: {} 메뉴 이름: {}",menuId ,targetName);
                    } catch (NumberFormatException e) {
                        targetName = ""; // 숫자 아니면 이름 못 불러옴
                    }
                    break;
                case "admin":
                    Admin admin = adminUserService.findByAdminId(targetIdStr);
                    targetName = (admin != null) ? admin.getAdminId() : "";
                    break;
                case "category":
                    try {
                        int categoryId = Integer.parseInt(targetIdStr);
                        Category category = adminCategoryService.getCategoryById(categoryId);
                        targetName = (category != null) ? category.getName() : "";
                    } catch (NumberFormatException e) {
                        targetName = "";
                    }
                    break;
                case "user":
                    try {
                        Admin user = adminUserService.findByAdminId(targetIdStr);
                        targetName = (user != null) ? user.getAdminId() : "";
                    } catch (Exception e) {
                        targetName = "";
                    }
                    break;
                case "option":
                    try{
                        int optionId = Integer.parseInt(targetIdStr);
                        MenuOptionDTO menuOptionDTO = adminOptionService.getOptionById(optionId);
                        targetName = (menuOptionDTO != null) ? menuOptionDTO.getOptionName() : "";
                    } catch (Exception e) {
                        targetName = "";
                    }
            }
            return AdminLogResponseDTO.builder()
                    .logId(adminLog.getLogId())
                    .actionType(adminLog.getActionType())
                    .targetTable(adminLog.getTargetTable())
                    .targetId(adminLog.getTargetId())
                    .targetName(targetName)
                    .description(adminLog.getDescription())
                    .adminId(adminLog.getAdminId())
                    .createdAt(adminLog.getCreatedAt())
                    .build();
        }).toList();

        log.info("히스토리 리스트: {}", list);
        return list;
    }

    public int countAdminLogs(Map<String,Object> params) {
        return adminLogMapper.countAdminLogs(params);
    }


    public List<AdminLogResponseDTO> getTenLogs(){
        List<AdminLog> logs = adminLogMapper.getTenHistory();

        List<AdminLogResponseDTO> list = logs.stream().map(adminLog -> {
            String targetIdStr = adminLog.getTargetId();  // 이미 String이라고 가정
            String targetName = "";

            switch (adminLog.getTargetTable()) {
                case "menu":
                    try {
                        int menuId = Integer.parseInt(targetIdStr);  // 숫자인 경우만 처리
                        AdminMenuDTO menu = adminMenuService.getMenuById(menuId);
                        targetName = (menu != null) ? menu.getName() : "";
                    } catch (NumberFormatException e) {
                        targetName = ""; // 숫자 아니면 이름 못 불러옴
                    }
                    break;
                case "admin":
                    Admin admin = adminUserService.findByAdminId(targetIdStr);
                    targetName = (admin != null) ? admin.getAdminId() : "";
                    break;
                case "category":
                    try {
                        int categoryId = Integer.parseInt(targetIdStr);
                        Category category = adminCategoryService.getCategoryById(categoryId);
                        targetName = (category != null) ? category.getName() : "";
                    } catch (NumberFormatException e) {
                        targetName = "";
                    }
                    break;
                case "user":
                    try {
                        Admin user = adminUserService.findByAdminId(targetIdStr);
                        targetName = (user != null) ? user.getAdminId() : "";
                    } catch (Exception e) {
                        targetName = "";
                    }
                    break;
                case "option":
                    try{
                        int optionId = Integer.parseInt(targetIdStr);
                        MenuOptionDTO menuOptionDTO = adminOptionService.getOptionById(optionId);
                        targetName = (menuOptionDTO != null) ? menuOptionDTO.getOptionName() : "";
                    } catch (Exception e) {
                        targetName = "";
                    }
            }
            return AdminLogResponseDTO.builder()
                    .logId(adminLog.getLogId())
                    .actionType(adminLog.getActionType())
                    .targetTable(adminLog.getTargetTable())
                    .targetId(adminLog.getTargetId())
                    .targetName(targetName)
                    .description(adminLog.getDescription())
                    .adminId(adminLog.getAdminId())
                    .createdAt(adminLog.getCreatedAt())
                    .build();
        }).toList();

        log.info("히스토리 리스트: {}", list);
        return list;
    }

    public List<CountLogByAdmin> getLogsByAdmin() {
        return adminLogMapper.countLogsByAdminId();
    }

    public List<CountLogByMonth> getLogsByMonth() {
        return adminLogMapper.countLogsByMonth();
    }

    public List<CountLogByActionType> getLogsByActionType() {
        return adminLogMapper.countLogsByActionType();
    }

    public List<CountLogByWeek> getLogsByWeek(String adminId) {
        return adminLogMapper.countLogsByWeekAdminId(adminId);
    }




}
