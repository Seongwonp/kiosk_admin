package com.cafe_kiosk.kiosk_admin.controller;


import com.cafe_kiosk.kiosk_admin.dto.category.Category;
import com.cafe_kiosk.kiosk_admin.dto.menu.AdminMenuDTO;
import com.cafe_kiosk.kiosk_admin.dto.user.Admin;
import com.cafe_kiosk.kiosk_admin.service.AdminCategoryService;
import com.cafe_kiosk.kiosk_admin.service.AdminMenuService;
import com.cafe_kiosk.kiosk_admin.service.AdminUserService;
import com.cafe_kiosk.kiosk_admin.util.ImageUploader;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/menu")
@Log4j2
public class AdminMenuController {

    private final AdminMenuService adminMenuService;
    private final AdminUserService adminUserService;
    private final AdminCategoryService adminCategoryService;
    private final ImageUploader imageUploader;

    @Value("${file.upload.path}")
    private String uploadPath; // 파일 경로
    @ModelAttribute("currentAdmin")
    public Admin currentAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return adminUserService.findByAdminId(auth.getName());
    }

    @GetMapping("/add")
    public String showAddMenuForm(Model model) {
        model.addAttribute("menu", new AdminMenuDTO());
        model.addAttribute("categoryList", adminCategoryService.getAllCategory());
        return "admin/menu/add";
    }

    @PostMapping("/add")
    public String addMenu(@ModelAttribute("menu") AdminMenuDTO menuDTO,
                          @RequestParam("imageFile") MultipartFile imageFile) {

        // 이미지 처리 로직
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = imageUploader.upload(imageFile, String.valueOf(menuDTO.getCategoryId()));
            menuDTO.setImageUrl(imageUrl);
        }
        adminMenuService.addMenu(menuDTO);
        return "redirect:/admin/menu/list"; // 등록 후 리스트로 이동
    }


    @GetMapping("/list")
    public String menuList(@RequestParam(required = false) String searchType,
                           @RequestParam(required = false) String keyword,
                           @RequestParam(required = false) String sortOrder,
                           @RequestParam(required = false) Integer categoryId,
                           Model model,
                           HttpServletRequest request) {

        Map<String, Object> search = new HashMap<>();
        search.put("searchType", searchType);
        search.put("keyword", keyword);
        search.put("sortOrder", sortOrder);

        List<AdminMenuDTO> menuList = adminMenuService.getMenuList(search);
        List<Category> categoryList = adminCategoryService.getAllCategory();
        List<Map<String, Object>> stats = adminMenuService.getCategoryStatsAll();
        List<AdminMenuDTO> soldOutList = adminMenuService.getSoldOutMenuList(categoryId);
        List<AdminMenuDTO> lowStockList = adminMenuService.getLowStockMenuList();

        int soldOutCount = adminMenuService.getSoldOutMenuCount();
        int lowStockCount = adminMenuService.getLowStockMenuCount();
        int totalCount = adminMenuService.getTotalCount();


        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menuList", menuList);
        model.addAttribute("categoryStats", stats);
        model.addAttribute("soldOutList", soldOutList);
        model.addAttribute("lowStockList", lowStockList);

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("soldOutCount", soldOutCount);
        model.addAttribute("lowStockCount", lowStockCount);

        return "admin/menu/list";
    }

    @GetMapping("/modify")
    public String modifyMenu(@RequestParam int menuId, Model model) {
        AdminMenuDTO menu = adminMenuService.getMenuById(menuId);
        List<Category> categoryList = adminCategoryService.getAllCategory();

        model.addAttribute("menu", menu);
        model.addAttribute("categoryList", categoryList);
        return "admin/menu/modify"; // 수정 폼 뷰 이름 (수정 가능)
    }

    @PostMapping("/update")
    public String updateMenu(@ModelAttribute AdminMenuDTO menuDto,
                              @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = imageUploader.upload(imageFile, String.valueOf(menuDto.getCategoryId()));
            menuDto.setImageUrl(imageUrl);
        } else {
            AdminMenuDTO existingMenu = adminMenuService.getMenuById(menuDto.getMenuId());
            menuDto.setImageUrl(existingMenu.getImageUrl());
        }
        adminMenuService.updateMenu(menuDto);
        return "redirect:/admin/menu/list";
    }

    @PostMapping("/delete")
    public String deleteMenu(@RequestParam int menuId) {
        AdminMenuDTO menu = adminMenuService.getMenuById(menuId);
        String imgUrl = menu.getImageUrl();
        log.info("Delete menu: {}, MenuImg: {}", menuId, imgUrl);
        if(imgUrl != null && !imgUrl.isEmpty()) {
            imageUploader.delete(imgUrl);
        }

        adminMenuService.deleteMenuById(menuId);
        return "redirect:/admin/menu/list";
    }

    @PostMapping("/category/add")
    public String addCategory(
            @RequestParam String categoryName,
            @RequestParam String categoryDescription,
            RedirectAttributes redirectAttributes) {
        try {
            adminCategoryService.addCategory(categoryName, categoryDescription);
            redirectAttributes.addFlashAttribute("message", "카테고리가 추가되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/menu/list";
    }
}
