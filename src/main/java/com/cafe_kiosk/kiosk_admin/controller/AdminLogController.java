package com.cafe_kiosk.kiosk_admin.controller;

import com.cafe_kiosk.kiosk_admin.dto.log.AdminLogResponseDTO;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByActionType;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByAdmin;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByMonth;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByWeek;
import com.cafe_kiosk.kiosk_admin.service.AdminLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/admin/history")
@RequiredArgsConstructor
public class AdminLogController {

    private final AdminLogService adminLogService;

    @GetMapping("/list")
    public String getAdminLogs(
            @RequestParam(required = false) String adminId,
            @RequestParam(required = false) String targetTable,
            @RequestParam(required = false) String actionType,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(defaultValue = "desc") String order,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {
        Map<String, Object> search = new HashMap<>();

        String loginId = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            loginId = userDetails.getUsername();
        }

        // 검색조건
        if (adminId != null && !adminId.isEmpty()) search.put("adminId", adminId);
        if (targetTable != null && !targetTable.isEmpty()) search.put("targetTable", targetTable);
        if (actionType != null && !actionType.isEmpty()) search.put("actionType", actionType);
        if (startDate != null && !startDate.isEmpty()) search.put("startDate", startDate + " 00:00:00");
        else search.put("startDate", null);

        if (endDate != null && !endDate.isEmpty()) search.put("endDate", endDate + " 23:59:59.999999");
        else search.put("endDate", null);
        search.put("order", order);

        // 페이징
        int offset = (page - 1) * size;
        search.put("offset", offset);
        search.put("size", size);

        List<AdminLogResponseDTO> logs = adminLogService.getAllAdminLogs(search);
        int totalCount = adminLogService.countAdminLogs(search); // 전체 개수 쿼리

        // 페이징 계산
        int totalPages = (int) Math.ceil((double) totalCount / size);

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);

        List<CountLogByAdmin> logsByAdmin = adminLogService.getLogsByAdmin();          // 관리자별
        List<CountLogByActionType> logsByAction = adminLogService.getLogsByActionType(); // 액션유형별
        List<CountLogByMonth> logsByMonth = adminLogService.getLogsByMonth();           // 월별
        List<CountLogByWeek> logsByWeek = adminLogService.getLogsByWeek(loginId);       // 주간
        // 관리자별 로그 통계
        List<String> adminLabels = logsByAdmin.stream()
                .map(CountLogByAdmin::getAdminId)
                .toList();
        List<Integer> adminCounts = logsByAdmin.stream()
                .map(CountLogByAdmin::getLogCount)
                .toList();
        model.addAttribute("adminLabels", adminLabels);
        model.addAttribute("adminCounts", adminCounts);

// 액션 타입별 로그 통계
        List<String> actionLabels = logsByAction.stream()
                .map(CountLogByActionType::getActionType)
                .toList();
        List<Integer> actionCounts = logsByAction.stream()
                .map(CountLogByActionType::getLogCount)
                .toList();
        model.addAttribute("actionLabels", actionLabels);
        model.addAttribute("actionCounts", actionCounts);

// 월별 로그 통계
        List<String> monthLabels = logsByMonth.stream()
                .map(CountLogByMonth::getMonth)  // getMonth()가 문자열이면
                .toList();
        List<Integer> monthCounts = logsByMonth.stream()
                .map(CountLogByMonth::getLogCount)
                .toList();
        model.addAttribute("monthLabels", monthLabels);
        model.addAttribute("monthCounts", monthCounts);

// 주간 로그 통계
        List<String> weekLabels = logsByWeek.stream()
                .map(CountLogByWeek::getWeek) // getWeek()가 문자열이면
                .toList();
        List<Integer> weekCounts = logsByWeek.stream()
                .map(CountLogByWeek::getLogCount)
                .toList();

        log.info("계정 정보: {}", loginId);
        log.info("계정기준 주간 내역: {}", logsByWeek);
        model.addAttribute("weekLabels", weekLabels);
        model.addAttribute("weekCounts", weekCounts);

        model.addAttribute("search", search);
        model.addAttribute("logs", logs);
        model.addAttribute("logCount", logs.size());


        return "admin/history/list";
    }
}