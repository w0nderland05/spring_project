package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.QReport;
import org.study.entities.Report;
import org.study.entities.User;

public interface ReportRepository extends JpaRepository<Report, Long>, QuerydslPredicateExecutor {

    /**
     * 신고 등록 여부 체크
     *
     * @param code
     * @return
     */
    default boolean exists(Long code) {
        QReport report = QReport.report;
        return exists(report.code.eq(code));
    };
//    Report findByCsCode(Long code);
}
