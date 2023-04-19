package org.study.repositories.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.board.BoardSkin;

public interface BoardSkinRepository extends JpaRepository<BoardSkin, String>, QuerydslPredicateExecutor {
}
