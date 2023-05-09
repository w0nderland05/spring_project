package org.study.controllers.admin.community;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.controllers.user.Community.PostConfig;
import org.study.entities.board.BoardData;
import org.study.models.Community.PostListService;
import org.study.repositories.board.BoardDataRepository;

import java.util.List;

/**
 *  <게시판 관리> -> 게시판 등록 을 통해서 게시판을 등록할 예정입니다.
 *  이 페이지는 게시판 분류 없이 모든 게시글을 조회할 수 있는 페이지로 구성되어 있습니다.
 *
 */

@Controller
@RequestMapping("/admin/community")
public class CommunityController {

    @Autowired
    private PostListService listService;
    @Autowired
    private BoardDataRepository boardDataRepository;
    @Autowired
    private HttpServletRequest request;

    /**
     * 커뮤니티 게시글 목록
     *
     * @return
     */
    @GetMapping
    public String lists(Model model, PostConfig postConfig, CommunitySearch communitySearch) {
        String url = request.getContextPath() + "/admin/community";
        List<BoardData> datas = listService.gets(communitySearch, 1, 20);
        return "admin/community/lists";
    }


    /**
     *  게시글 상세 보기 -> user에서 보여지는 게시글로 이동 (getmapping x)
     */

    /**
     *  게시글 수정
     */



}
