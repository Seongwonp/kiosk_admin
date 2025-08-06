package com.cafe_kiosk.kiosk_admin.service;

import com.cafe_kiosk.kiosk_admin.annotation.AdminLoggable;
import com.cafe_kiosk.kiosk_admin.dto.category.MenuOptionDTO;
import com.cafe_kiosk.kiosk_admin.mapper.AdminOptionMapper;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminOptionService {

    private final AdminOptionMapper adminOptionMapper;

    public List<MenuOptionDTO> getOptionsByCategoryWithName(Integer categoryId) {
        return adminOptionMapper.selectOptionsWithCategoryName(categoryId);
    }

    public MenuOptionDTO getOptionById(int optionId) {
        return adminOptionMapper.getOptionById(optionId);
    }

    @AdminLoggable(actionType = "INSERT", targetTable = "option", description = "옵션 추가")
    public void insertOption(MenuOptionDTO option) {
        adminOptionMapper.insertOption(option);
    }

    @AdminLoggable(actionType = "UPDATE", targetTable = "option", description = "옵션 수정")
    public void updateOption(MenuOptionDTO option) {
        adminOptionMapper.updateOption(option);
    }

    @AdminLoggable(actionType = "UPDATE", targetTable = "option", description = "옵션 수정")
    public void updateIsDeleted(int optionId, boolean isDeleted) {
        adminOptionMapper.updateIsDeleted(optionId, isDeleted);
    }
}
