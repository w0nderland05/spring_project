package org.study.controllers.admin.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/community")
public class CommunityController {

    /**
     * 커뮤니티 게시글 목록
     *
     * @return
     */
    @GetMapping
    public String lists(){
        return "admin/community/lists";
    }

}
