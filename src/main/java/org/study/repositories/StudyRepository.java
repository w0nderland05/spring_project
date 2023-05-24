package org.study.repositories;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.commons.constants.RegionType;
import org.study.commons.constants.Status;
import org.study.controllers.admin.study.StudySearch;
import org.study.entities.QCategory;
import org.study.entities.QStudy;
import org.study.entities.Study;

import java.util.Arrays;
import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long>, QuerydslPredicateExecutor {
/**
    default boolean exists(Long studyCode) {
        QStudy study = QStudy.study;
        return exists(study.studyCode.eq(studyCode));
    }



    //  UserNm 만 가져오려면
    // List<UserMapping> findByEmail();
*/

}
