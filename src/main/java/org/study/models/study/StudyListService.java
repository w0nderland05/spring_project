package org.study.models.study;

import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.study.commons.constants.Status;
import org.study.commons.validators.StudyNotFoundException;
import org.study.controllers.admin.board.BoardConfig;
import org.study.controllers.admin.study.StudyConfig;
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
                .approveStatus(study.getApproveStatus())
                .regStatusDt(study.getRegStatusDt())
                .maxMember(study.getMaxMember())
                .remainSeat(study.getRemainSeat())
                .activeStatus(study.isActiveStatus())
                .numOfWeek(study.getNumOfWeek())
                .regionType(study.getRegionType())
                .simpleIntro(study.getSimpleIntro())
                .introduction(study.getIntroduction())
                .build();
    }

    public List<Study> gets() {
        return gets(null).getContent();
    }

    public Page<Study> gets(StudyConfig studyConfig) {
        BooleanBuilder builder = new BooleanBuilder();
        QStudy study = QStudy.study;
        /** 검색 조건 처리 S */

        /** 검색 조건 처리 E */

        int page = studyConfig.getPage();
        int limit = studyConfig.getLimit();
        page = page < 1 ? 1 : page;
        limit = limit < 1 ? 20 : limit;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(desc("requestDt")));
        Page<Study> pageData = repository.findAll(builder, pageable);

        return pageData;
    }


    public StudyConfig get(Long studyCode) {
        if (studyCode == null) {
            throw new StudyNotFoundException();
        }

        Study study = repository.findById(studyCode).orElseThrow(StudyNotRegisteredException::new);


        return toConfig(study);
    }

    public List <StudyConfig> applyStatusGets(Status status){
        List<Study> studies= repository.findByApproveStatus(status);
        List <StudyConfig> configs = studies.stream().map(this::toConfig).toList();
        return configs;
    }
}
