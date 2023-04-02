package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.User;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor {
    // 아이디로 회원 정보 조회
    User findByUserId(String userId);
}
