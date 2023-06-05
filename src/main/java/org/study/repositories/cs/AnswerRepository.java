package org.study.repositories.cs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>, QuerydslPredicateExecutor {
}
