package org.study.repositories.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.QCategory;
import org.study.entities.board.Board;
import org.study.entities.board.QBoard;

public interface BoardRepository extends JpaRepository<Board, String>, QuerydslPredicateExecutor {
    /**
     * 게시판 등록 여부 체크
     *
     * @param bId
     * @return
     */
    default boolean exists(String bId) {
        QBoard board = QBoard.board;
        return exists(board.bId.eq(bId));
    }
}
