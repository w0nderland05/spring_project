package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.Category;
import org.study.entities.QCategory;

public interface CategoryRepository extends JpaRepository<Category, String>, QuerydslPredicateExecutor {

    /**
     * 카테고리 등록 여부 체크
     *
     * @param cateCd
     * @return
     */
    default boolean exists(String cateCd) {
        QCategory category = QCategory.category;
        return exists(category.cateCd.eq(cateCd));
    }
}
