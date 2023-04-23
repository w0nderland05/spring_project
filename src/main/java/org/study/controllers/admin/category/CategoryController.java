package org.study.controllers.admin.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.models.category.CateSaveService;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CateSaveService service;

    /**
     * <카테고리 관리>클릭시 나오는 페이지
     * == 카테고리 목록
     *
     * @return
     */
    @GetMapping
    public String index(){
        return "admin/category/index";


    }

    /**
     * 카테고리 관리 -> 카테고리 등록
     *
     * @return
     */
    @GetMapping("/register")
    public String register(Model model){

        CategoryForm categoryForm = new CategoryForm();
        model.addAttribute("categoryForm", categoryForm);

        return "admin/category/register";
    }


    /**
     * 카테고리 저장(추가, 수정) 처리
     *
     * @param categoryForm
     * @param errors
     * @return
     */
    @PostMapping
    public String save(@Valid CategoryForm categoryForm, Errors errors) {

        // 카테고리 저장 처리
        service.save(categoryForm, errors);

        if (errors.hasErrors()) {
            return "admin/category/register";
        }

        return "redirect:/admin/category";
    }
}
