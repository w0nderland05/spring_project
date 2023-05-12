package org.study.repositories.cs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>, QuerydslPredicateExecutor {
}
