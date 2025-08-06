package com.cafe_kiosk.kiosk_admin.mapper;


import com.cafe_kiosk.kiosk_admin.dto.log.AdminLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface AdminHistoryMapper {
    void insert(AdminLog adminLog);
}

