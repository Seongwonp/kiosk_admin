package com.cafe_kiosk.kiosk_admin.mapper;

import com.cafe_kiosk.kiosk_admin.dto.user.Admin;
import com.cafe_kiosk.kiosk_admin.dto.user.AdminRoleStats;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface AdminUserMapper {
    void insert(Admin admin);
    Admin findByAdminId(String adminId);
    void modifySelfPassword(Admin admin);
    void modifyByCEO(Admin admin);
    List<Admin> adminList(Map<String, Object> map);
    List<AdminRoleStats> adminRoleCount();
}

