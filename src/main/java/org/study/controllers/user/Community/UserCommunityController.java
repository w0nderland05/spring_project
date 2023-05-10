package org.study.controllers.user.Community;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.commons.validators.CommonException;
import org.study.controllers.admin.board.BoardConfig;
import org.study.models.Community.DuplicatePostGidException;
import org.study.models.Community.PostConfigService;
import org.study.models.Community.PostInfoService;
import org.study.models.Community.PostListService;
import org.study.repositories.board.BoardDataRepository;

import java.util.List;

@Controller
@RequestMapping("/user/community")
public class UserCommunityController {

    @Autowired
    private PostListService listService;
    @Autowired
    private BoardDataRepository boardDataRepository;
    @Autowired
    private PostConfigService postConfigService;
    @Autowired
    private PostInfoService postInfoService;

    /**
     * <커뮤니티> 클릭하면 나오는 페이지
     *
     * @return
     */
    @GetMapping
    public String community(Model model) {
        List<PostConfig> configs = listService.gets();
        model.addAttribute("configs", configs);

//        String category = boardConfig.getCategory();
//        String tpl = "user/community/";
//        if (category.equals("qna")) {
//            tpl += "qna";
//            return tpl;
//        } else if (category.equals("free")) {
//            tpl += "free";
//            return tpl;
//        }

        return "front/community/community";
    }

    @GetMapping("/qna")
    public String qna(Model model) {
        List<PostConfig> configs = listService.gets();
        model.addAttribute("configs", configs);

        return "front/community/qna";
    }

    @GetMapping("/free")
    public String free(Model model) {
        List<PostConfig> configs = listService.gets();
        model.addAttribute("configs", configs);

        return "front/community/free";
    }

    @GetMapping("/register")
    public String register(Model model) {
        PostConfig postConfig = new PostConfig();
        model.addAttribute("postConfig", postConfig);

        return "front/community/register";
    }

    @PostMapping("/save")
    public String save(@Valid PostConfig postConfig, Errors errors) {
        try {
            postConfigService.postConfig(postConfig, errors);
        } catch (DuplicatePostGidException e) {
            errors.rejectValue("gid", "Duplicate.postConfig.gid");
        }

        String mode = postConfig.getMode();
        if (errors.hasErrors()) {
            String tpl = "front/community/";
            if (mode != null && mode.equals("update")) {
                tpl += "update";
            } else {
                tpl += "register";
            }
            return tpl;
        }
        postConfigService.postConfig(postConfig);

        return "redirect:/user/community";
    }

    /**
     * 게시판 상세 -> 게시판 수정 & 삭제 가능
     *
     * @return
     */
    @GetMapping("/update/{gid}")
    public String update(@PathVariable Long gid, Model model, HttpServletResponse response) {
        model.addAttribute("mode", "update");

        try {
            PostConfig postConfig = postInfoService.get(gid);
            model.addAttribute("postConfig", postConfig);
        } catch (CommonException e) {
            response.setStatus(e.getStatus().value());
            model.addAttribute("script", "alert('" + e.getMessage() + "');history.back();");
            return "commons/execute_script";
        }
        return "front/community/update";
    }
}
