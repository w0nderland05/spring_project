package org.study.repositories.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.board.BoardComment;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long>, QuerydslPredicateExecutor {
}
