package org.study.controllers.admin.category;

import org.springframework.stereotype.Controller;
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
    public String register(){
        return "admin/category/register";
    }



}
