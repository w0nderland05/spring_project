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
}
