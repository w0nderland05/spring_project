package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.Studies;

/**
 * 스터디 관련
 * 
 */
public interface StudiesRepository extends JpaRepository<Studies, Long>, QuerydslPredicateExecutor<Studies> {

}
