package com.cafe_kiosk.kiosk_admin.mapper;

import com.cafe_kiosk.kiosk_admin.dto.log.AdminLog;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByActionType;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByAdmin;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByMonth;
import com.cafe_kiosk.kiosk_admin.dto.log.chart.CountLogByWeek;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminLogMapper {
    void insertAdminLog(AdminLog adminLog);
    List<AdminLog> getAllAdminLogs(Map<String, Object> map);
    int countAdminLogs(Map<String, Object> map);
    List<AdminLog> getTenHistory();

    List<CountLogByAdmin> countLogsByAdminId();
    List<CountLogByMonth> countLogsByMonth();
    List<CountLogByActionType> countLogsByActionType();

    List<CountLogByWeek> countLogsByWeekAdminId(String adminId);
}
