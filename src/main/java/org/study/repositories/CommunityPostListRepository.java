package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.Community;
import org.study.entities.CommunityPostList;

public interface CommunityPostListRepository extends JpaRepository<CommunityPostList, Long>, QuerydslPredicateExecutor<Community> {
    CommunityPostList findByCode(Long code);
}
