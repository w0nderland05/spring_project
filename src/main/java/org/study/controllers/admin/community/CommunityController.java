package org.study.controllers.admin.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  <게시판 관리> -> 게시판 등록 을 통해서 게시판을 등록할 예정입니다.
 *  이 페이지는 게시판 분류 없이 모든 게시글을 조회할 수 있는 페이지로 구성되어 있습니다.
 *
 */

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


    /**
     *  게시글 상세 보기 -> user에서 보여지는 게시글로 이동 (getmapping x)
     */

    /**
     *  게시글 수정
     */



}
