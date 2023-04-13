package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.Community;
import org.study.entities.CommunityPostList;

<<<<<<< HEAD
public interface CommunityPostListRepository extends JpaRepository<CommunityPostList, Long>, QuerydslPredicateExecutor<Community> {
    CommunityPostList findByCode(Long code);
=======
public interface CommunityPostListRepository extends JpaRepository<Community, Long>, QuerydslPredicateExecutor<Community> {

>>>>>>> 5fd627507ace88f9ef256ba89f5f60c755d41bd9
}
