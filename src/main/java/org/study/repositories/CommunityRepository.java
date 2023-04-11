package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.Community;

public interface CommunityRepository extends JpaRepository<Community, String>, QuerydslPredicateExecutor<Community> {

}
