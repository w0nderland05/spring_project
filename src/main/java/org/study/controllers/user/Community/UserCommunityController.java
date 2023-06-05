package org.study.controllers.user.Community;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.study.commons.Pagination;
import org.study.commons.UserUtils;
import org.study.commons.validators.CommonException;
import org.study.controllers.admin.community.CommunitySearch;
import org.study.entities.board.BoardData;
import org.study.models.Community.DuplicatePostGidException;
import org.study.models.Community.PostSaveService;
import org.study.models.Community.PostInfoService;
import org.study.models.Community.PostListService;
import org.study.repositories.board.BoardDataRepository;

@Controller
@RequestMapping("/user/community")
public class UserCommunityController {

    @Autowired
    private PostListService listService;
    @Autowired
    private BoardDataRepository boardDataRepository;
    @Autowired
    private PostSaveService postSaveService;
    @Autowired
    private PostInfoService postInfoService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserUtils userUtils;

    /**
     * <커뮤니티> 클릭하면 나오는 페이지
     *
     * @return
     */
    @GetMapping
    public String community(Model model, PostConfig postConfig, CommunitySearch communitySearch) {
        String url = request.getContextPath() + "/user/community";
        postConfig.setUser(userUtils.getEntity());
        Page<BoardData> data = listService.gets(communitySearch);
        Pagination<BoardData> pagination = new Pagination<>(data, url);

        model.addAttribute("datas", data.getContent());

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
    public String qna(Model model, PostConfig postConfig, CommunitySearch communitySearch) {
        String url = request.getContextPath() + "/user/community/qna";
        postConfig.setUser(userUtils.getEntity());
        Page<BoardData> data = listService.gets(communitySearch);
        Pagination<BoardData> pagination = new Pagination<>(data, url);
        model.addAttribute("datas", data.getContent());

        return "front/community/qna";
    }

    @GetMapping("/free")
    public String free(Model model, PostConfig postConfig, CommunitySearch communitySearch) {
        String url = request.getContextPath() + "/user/community/free";
        postConfig.setUser(userUtils.getEntity());
        Page<BoardData> data = listService.gets(communitySearch);
        Pagination<BoardData> pagination = new Pagination<>(data, url);
        model.addAttribute("datas", data.getContent());

        return "front/community/free";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute PostConfig postConfig) {

        return "front/community/register";
    }

    @PostMapping("/save")
    public String save(@Valid PostConfig postConfig, Errors errors, Model model) {
        model.addAttribute("postConfig", postConfig);
        try {
            postSaveService.postConfig(postConfig, errors);
        } catch (DuplicatePostGidException e) {
            errors.rejectValue("gid", "Duplicate.postConfig.gid");
        }

        postSaveService.postConfig(postConfig);
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
