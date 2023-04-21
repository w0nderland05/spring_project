package org.study.controllers.user.Community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/community")
public class UserCommunityController {
    /**
     * <커뮤니티> 클릭하면 나오는 페이지
     *
     * @return
     */
    @GetMapping
    public String community() {return "front/community/community";}

    @GetMapping("/register")
    public String register() {return "front/community/register";}
}
