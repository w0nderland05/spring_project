package org.study.controllers.admin.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

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
    public String register(){
        return "admin/category/register";
    }



}
