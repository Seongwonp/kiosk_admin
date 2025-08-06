package com.cafe_kiosk.kiosk_admin.mapper;


import com.cafe_kiosk.kiosk_admin.dto.category.MenuOptionDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AdminOptionMapper {
    MenuOptionDTO getOptionById(int optionId);
    void updateOption(MenuOptionDTO option);
    void insertOption(MenuOptionDTO option);
    void updateIsDeleted(@Param("optionId") int optionId, @Param("isDeleted") boolean isDeleted);
    List<MenuOptionDTO> selectOptionsWithCategoryName(Integer categoryId);
    List<MenuOptionDTO> selectMenuOptionsByIds(@Param("optionIds") List<Integer> optionIds);
}
