package org.study.controllers.admin.category;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    /**
     * 카테고리리스트
     * @return
     */
    @GetMapping
    public String CategoryList(){
        return "admin/category/index";


    }

    /**
     * 카테고리 등록  (세부목록 클릭시 )
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
        if (errors.hasErrors()) {
            return "admin/category/register";
        }


        return "redirect:/admin/category";
    }

}
