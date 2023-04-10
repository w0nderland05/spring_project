package org.study.controllers.admin.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/user")
public class UserController {

    /**
     * <회원관리> 클릭시 나오는 페이지
     * == 회원 목록이 보임
     *
     * @return
     */
    @GetMapping
    public String index() {
        return "admin/user/index";
    }

    /**
     * 회원 정보 상세
     *
     * @return
     */
    @GetMapping("/detail")
    public String detail(){
        return "admin/user/detail";

    }
}
