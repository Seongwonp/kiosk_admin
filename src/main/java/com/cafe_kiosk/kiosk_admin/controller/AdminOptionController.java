package com.cafe_kiosk.kiosk_admin.controller;

import com.cafe_kiosk.kiosk_admin.dto.category.Category;
import com.cafe_kiosk.kiosk_admin.dto.category.MenuOptionDTO;
import com.cafe_kiosk.kiosk_admin.service.AdminCategoryService;
import com.cafe_kiosk.kiosk_admin.service.AdminOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/option")
public class AdminOptionController {

    private final AdminOptionService adminOptionService;
    private final AdminCategoryService adminCategoryService;


    @GetMapping("/list")
    public String listOptions(@RequestParam(required = false) Integer categoryId, Model model) {
        List<Category> categoryList = adminCategoryService.getAllCategory();
        model.addAttribute("categoryList", categoryList);

        List<MenuOptionDTO> optionList = adminOptionService.getOptionsByCategoryWithName(
                categoryId == null ? 0 : categoryId);

        model.addAttribute("optionList", optionList);
        model.addAttribute("categoryId", categoryId == null ? 0 : categoryId);
        return "admin/option/list";
    }

    @PostMapping("/add")
    public String addOption(@ModelAttribute MenuOptionDTO option, @RequestParam("categoryId") int categoryId, RedirectAttributes redirectAttributes) {
        option.setCategoryId(categoryId);
        adminOptionService.insertOption(option);
        redirectAttributes.addFlashAttribute("message", "옵션이 성공적으로 추가되었습니다.");
        return "redirect:/admin/option/list?categoryId=" + categoryId;
    }

    @PostMapping("/update")
    public String updateOption(@ModelAttribute MenuOptionDTO option, RedirectAttributes redirectAttributes) {
        adminOptionService.updateOption(option);
        redirectAttributes.addFlashAttribute("message", "옵션이 성공적으로 수정되었습니다.");
        return "redirect:/admin/option/list?categoryId=" + option.getCategoryId();
    }

    @PostMapping("/delete")
    public String deleteOption(@RequestParam("optionId") int optionId,
                               @RequestParam("categoryId") int categoryId,
                               RedirectAttributes redirectAttributes) {

        adminOptionService.updateIsDeleted(optionId, true);

        redirectAttributes.addFlashAttribute("toastMessage", "옵션이 삭제되었습니다.");
        return "redirect:/admin/option/list?categoryId=" + categoryId;
    }

    @PostMapping("/toggle-delete")
    public String toggleDelete(@RequestParam int optionId, @RequestParam boolean isDeleted, RedirectAttributes redirectAttributes) {
        System.out.println("optionId = " + optionId + ", isDeleted = " + isDeleted);
        adminOptionService.updateIsDeleted(optionId, isDeleted);
        redirectAttributes.addFlashAttribute("message", "옵션 상태가 변경되었습니다.");
        return "redirect:/admin/option/list";
    }
}
