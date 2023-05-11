package org.study.repositories.board;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.commons.constants.RegionType;
import org.study.controllers.admin.community.CommunitySearch;
import org.study.controllers.admin.study.StudySearch;
import org.study.entities.QStudy;
import org.study.entities.Study;
import org.study.entities.board.BoardData;
import org.study.entities.board.QBoardData;

import java.util.Arrays;
import java.util.List;

public interface BoardDataRepository extends JpaRepository<BoardData, Long>, QuerydslPredicateExecutor {

    /** 게시글 등록 여부 체크 */
    default boolean exists(Long gid) {
        QBoardData boardData = QBoardData.boardData;
        return exists(boardData.gid.eq(gid));
    }

    /** 카테고리명이 질문과 답변일 때 해당 게시글 조회 */

    /** 사용자 페이지 내에서 조회
     *  제목, 내용, 작성자 조건 검색
     */

    default Page<BoardData> search(CommunitySearch communitySearch) {
        /* 페이징 처리 S*/
        int page = communitySearch.getPage();
        page = page < 1 ? 1 : page;
        int limit = communitySearch.getLimit();
        limit = limit < 1 ? 20 : limit;

        Pageable pageable = PageRequest.of(page - 1, limit);
        /* 페이징 처리 E*/

        /*검색조건 처리 S*/
        BooleanBuilder builder = new BooleanBuilder();
        QBoardData boardData = QBoardData.boardData;
        String sopt = communitySearch.getSopt();
        String skey = communitySearch.getSkey();

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

        /*검색조건 처리 E*/
        Page<BoardData> data = findAll(builder, pageable);
        return data;
    }
}
