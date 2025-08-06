package com.cafe_kiosk.kiosk_admin.service;

import com.cafe_kiosk.kiosk_admin.annotation.AdminLoggable;
import com.cafe_kiosk.kiosk_admin.dto.category.Category;
import com.cafe_kiosk.kiosk_admin.mapper.AdminCategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final AdminCategoryMapper adminCategoryMapper;

    public List<Category> getAllCategory() {
        return adminCategoryMapper.getAllCategory();
    }

    public Category getCategoryById(int categoryId) {
        return adminCategoryMapper.getCategoryById(categoryId);
    }

    @AdminLoggable(actionType = "INSERT", targetTable = "category", description = "카테고리 추가")
    public void addCategory(String name, String description) {
        if (adminCategoryMapper.existsCategoryByName(name)) {
            throw new RuntimeException("이미 존재하는 카테고리 이름입니다.");
        }
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        adminCategoryMapper.insertCategory(category);
    }
}
