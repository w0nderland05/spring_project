package org.study.models.study;

import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.study.commons.constants.Status;
import org.study.commons.validators.StudyNotFoundException;
import org.study.controllers.admin.board.BoardConfig;
import org.study.controllers.admin.study.StudyConfig;
import org.study.controllers.admin.study.StudySearch;
import org.study.entities.QStudy;
import org.study.entities.Study;
import org.study.repositories.StudyRepository;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class StudyListService {

    private final StudyRepository repository;
    private final EntityManager em;


    private StudyConfig toConfig(Study study) {
        return StudyConfig.builder()
                .mode("create")
                .studyNm(study.getStudyNm())
                .category(study.getCategory())
                .requestDt(study.getRequestDt())
                .approveStatus(study.getApproveStatus().toString())
                .regStatusDt(study.getRegStatusDt())
                .maxMember(study.getMaxMember())
                .remainSeat(study.getRemainSeat())
                .activeStatus(study.isActiveStatus())
                .numOfWeek(study.getNumOfWeek())
                .regionType(study.getRegionType().toString())
                .simpleIntro(study.getSimpleIntro())
                .introduction(study.getIntroduction())
                .build();
    }


    public Page<Study> gets(StudySearch studySearch) { //전체조회, 검색조건이 있는경우 조회
        BooleanBuilder builder = new BooleanBuilder();
        QStudy study = QStudy.study;
        /** 검색 조건 처리 S */
        int page = studySearch.getPage();
        int limit = studySearch.getLimit();
        page = page < 1 ? 1 : page;
        limit = limit < 1 ? 20 : limit;

        Long studyCode = studySearch.getStudyCode();
        String sopt = studySearch.getSopt();
        String skey = studySearch.getSkey();
        String[] regionType = studySearch.getRegionType();
        String[] approveStatus = studySearch.getApproveStatus();

        if (studyCode != null) { //스터디코드
            builder.and(study.studyCode.in(studyCode));
        }
        if (sopt != null && !sopt.isBlank() && skey != null && !skey.isBlank()) {
            BooleanBuilder orBuilder = new BooleanBuilder();
            if (sopt.equals("studyNm")) {//스터디명
                orBuilder.or(study.studyNm.contains(skey));
            } else if (sopt.equals("category")) {//카테고리
                orBuilder.or(study.category.contains(skey));
            }
            builder.and(orBuilder);
        }
        if (approveStatus != null && approveStatus.length > 0) { //승인상태
            List<Status> approveStatuses = Arrays.stream(approveStatus).map(Status::valueOf).toList();
            builder.and(study.approveStatus.in(approveStatuses));
        }
   /** 검색 조건 처리 E */

        Pageable pageable = PageRequest.of(page, limit, Sort.by(desc("requestDt")));
        Page<Study> pageData = repository.findAll(builder, pageable);

        return pageData;

    }


    public StudyConfig get(Long studyCode) { //개별조회
        if (studyCode == null) {
            throw new StudyNotFoundException();
        }

        Study study = repository.findById(studyCode).orElseThrow(StudyNotRegisteredException::new);


        return toConfig(study);
    }

    public List<StudyConfig> applyStatusGets(Status status) {
        List<Study> studies = repository.findByApproveStatus(status);
        List<StudyConfig> configs = studies.stream().map(this::toConfig).toList();
        return configs;
    }
}
