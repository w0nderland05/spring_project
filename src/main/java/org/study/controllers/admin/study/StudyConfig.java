package org.study.controllers.admin.study;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.ModelMapper;
import org.study.commons.constants.RegionType;
import org.study.commons.constants.Status;
import org.study.controllers.admin.category.CategoryForm;
import org.study.entities.Category;
import org.study.entities.Study;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyConfig {

    private String mode; //update이면 수정모드

    @NotBlank
    private Long studyCode; //스터디코드

    @NotBlank(message = "스터디명을 입력하세요.")
    private String studyNm; //스터디명


    @NotBlank(message ="카테고리를 선택해주세요.")
    private String category; //카테고리

    private LocalDateTime requestDt;//개설신청일시


    private Status approveStatus; //승인상태

    private LocalDateTime regStatusDt;//상태처리일시

    @NotBlank(message = "신청최대인원수를 체크해주세요.")
    @Range(min =0, max =1000)
    private Long maxMember; //신청최대인원수

    private Long remainSeat; //남은 자리수


    private boolean activeStatus; //모집상태(모집중 - true, 모집마감(자리없음) -false)
    @NotBlank(message = "스터디 주당횟수를 선택해주세요.")
    private String numOfWeek; //스터디 주당횟수

    @NotBlank(message = "스터디 지역타입을 선택해주세요.")
    private RegionType regionType = RegionType.ONLINE; //지역(온라인/ 오프라인 -추후 프로그램내에서 시/도/군/구 값도 추가되도록 진행예정 )

    @NotBlank(message = "한줄 소개글을 입력해주세요.")
    private String simpleIntro; //한줄 소개글

    @NotBlank(message = "소개글을 작성해주세요.")
    private String Introduction; //소개글

    public static Study of (StudyConfig studyConfig) {
        return new ModelMapper().map(studyConfig, Study.class);
    }
}
