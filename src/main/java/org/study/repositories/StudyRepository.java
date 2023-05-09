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
     * 사용자페이지내에서 조회
     * 지역 ,스터디명, 카테고리 조건검색
     */
    default Page<Study> getStudyUserP(StudySearch studySearch) {
        /* 페이징 처리 S*/
        int page = studySearch.getPage();
        page = page < 1 ? 1 : page;
        int limit = studySearch.getLimit();
        limit = limit < 1 ? 20 : limit;

        Pageable pageable = PageRequest.of(page - 1, limit);
        /* 페이징 처리 E*/

        /*검색조건 처리 S*/
        BooleanBuilder builder = new BooleanBuilder();
        QStudy study = QStudy.study;
        String sopt = studySearch.getSopt();
        String skey = studySearch.getSkey();
        String[] regionType = studySearch.getRegionType();


        if (sopt != null && !sopt.isBlank() && skey != null && !skey.isBlank()) {
            BooleanBuilder orBuilder = new BooleanBuilder();
            if (sopt.equals("studyNm")) {//스터디명
                orBuilder.or(study.studyNm.contains(skey));
            } else if (sopt.equals("category")) {//카테고리
                orBuilder.or(study.category.contains(skey));
            }
            builder.and(orBuilder);
        }

        if (regionType != null && regionType.length > 0) { //지역별조회
            List<RegionType> regionTypes = Arrays.stream(regionType).map(RegionType::valueOf).toList();
            builder.and(study.regionType.in(regionTypes));
        }

        /*검색조건 처리 E*/

        Page<Study> data = findAll(builder, pageable);
        return data;
    }

    /**
     * 관리자페이지내에서 조회
     * 스터디코드,스터디명,승인상태 조건검색
     */
    default Page<Study> getStudyAdminP(StudySearch studySearch) {
        /* 페이징 처리 S*/
        int page = studySearch.getPage();
        page = page < 1 ? 1 : page;
        int limit = studySearch.getLimit();
        limit = limit < 1 ? 20 : limit;

        Pageable pageable = PageRequest.of(page - 1, limit);
        /* 페이징 처리 E*/

        /*검색조건 처리 S*/
        BooleanBuilder builder = new BooleanBuilder();
        QStudy study = QStudy.study;
        Long studyCode = studySearch.getStudyCode();
        String sopt = studySearch.getSopt();
        String skey = studySearch.getSkey();
        String[] approveStatus = studySearch.getApproveStatus();

        if (studyCode != null) { //스터디코드
            builder.and(study.studyCode.in(studyCode));
        }

        if (sopt != null && !sopt.isBlank() && skey != null && !skey.isBlank()) { //스터디명
            BooleanBuilder orBuilder = new BooleanBuilder();
            if (sopt.equals("studyNm")) { // 스터디명
                orBuilder.or(study.studyNm.contains(skey));
            }
            builder.and(orBuilder);
        }

        if(approveStatus != null && approveStatus.length>0){ //승인상태
            List<Status> approveStatuses = Arrays.stream(approveStatus).map(Status::valueOf).toList();
            builder.and(study.approveStatus.in(approveStatuses));
        }


        /*검색조건 처리 E*/

        Page<Study> data = findAll(builder, pageable);
        return data;

    }

    /**
     * 상태에 따른조회
     */
    List<Study> findByApproveStatus(Status status);

    default boolean exists(Long studyCode) {
        QStudy study = QStudy.study;
        return exists(study.studyCode.eq(studyCode));
    }


    //  UserNm 만 가져오려면
    // List<UserMapping> findByEmail();


}
