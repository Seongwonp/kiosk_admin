package com.cafe_kiosk.kiosk_admin.mapper;



import com.cafe_kiosk.kiosk_admin.dto.category.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AdminCategoryMapper {
    List<Category> getAllCategory();
    void insertCategory(Category category);
    Category getCategoryById(int categoryId);
    boolean existsCategoryByName(@Param("name") String name);
}

