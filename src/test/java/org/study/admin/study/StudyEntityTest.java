package org.study.admin.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.study.commons.constants.RegionType;
import org.study.entities.Study;
import org.study.repositories.StudyRepository;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class StudyEntityTest {

    @Autowired
    private StudyRepository studyRepository;

    @Test
    @DisplayName("study entity db등록")
    public void study(){

        Study study = Study.builder()
                .studyNm("공부해요")
                .category("study")
                .maxMember(10L)
                .numOfWeek(10L)
                .regionType(RegionType.ONLINE)
                .simpleIntro("안녕하세용~")
                .Introduction("Lob은 largeObject의 줄임말이었다...하하")
                .build();

        studyRepository.saveAndFlush(study);

    }







}
