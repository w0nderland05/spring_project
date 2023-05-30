package org.study.models.Community;

import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.commons.UserUtils;
import org.study.controllers.admin.community.CommunitySearch;
import org.study.controllers.user.Community.PostConfig;
import org.study.entities.board.BoardData;
import org.study.entities.board.QBoardData;
import org.study.repositories.board.BoardDataRepository;
import static org.springframework.data.domain.Sort.Order.desc;

/**
 * 게시글 목록 조회 서비스
 */

@Service
public class PostListService {

    @Autowired
    private BoardDataRepository boardDataRepository;

    @Autowired
    private UserUtils userUtils;

    private PostConfig toConfig(BoardData boardData) {
        return PostConfig.builder()
                .gid(boardData.getGid())
                .category(boardData.getCategory())
                .subject(boardData.getSubject())
                .build();
    }

    /** 사용자 페이지 내에서 조회
     *  제목, 내용, 작성자 조건 검색
     */

    public Page<BoardData> gets(CommunitySearch communitySearch) {
        BooleanBuilder builder = new BooleanBuilder();
        QBoardData boardData = QBoardData.boardData;

        /** 검색조건 처리 S */
        Long gid = communitySearch.getGid();
        String sopt = communitySearch.getSopt();
        String skey = communitySearch.getSkey();

        if (gid != null) { // 그룹아이디
            builder.and(boardData.gid.in(gid));
        }

        if (sopt != null && !sopt.isBlank() && skey != null && !skey.isBlank()) {
            BooleanBuilder orBuilder = new BooleanBuilder();
            if (sopt.equals("subject")) { // 제목
                orBuilder.or(boardData.subject.contains(skey));
            } else if (sopt.equals("content")) { // 내용
                orBuilder.or(boardData.content.contains(skey));
            } else if (sopt.equals("poster")) { // 작성자
                orBuilder.or(boardData.poster.contains(skey));
            }
            builder.and(orBuilder);
        }
        /** 검색조건 처리 E */

        /** 페이지 정렬 처리 S */
        int page = communitySearch.getPage();
        int limit = communitySearch.getLimit();
        page = page < 1 ? 1 : page;
        limit = limit < 1 ? 20 : limit;

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));
        Page<BoardData> pagedata = boardDataRepository.findAll(builder, pageable);
        /** 페이지 정렬 처리 E */

        return pagedata;
    }
}