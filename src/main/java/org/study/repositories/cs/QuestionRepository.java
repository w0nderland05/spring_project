package org.study.repositories.cs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.QQuestion;
import org.study.entities.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>, QuerydslPredicateExecutor {

    /**
     * 문의 여부 체크
     * @param code
     * @return
     */
    default boolean exists(Long code) {
        QQuestion question = QQuestion.question;
        return exists(question.qsCode.eq(code));
    }
}
