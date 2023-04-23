package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.Report;

public interface ReportRepository extends JpaRepository<Report, Long>, QuerydslPredicateExecutor {
}
