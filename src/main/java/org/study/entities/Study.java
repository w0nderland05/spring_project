package org.study.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.study.commons.constants.RegionType;
import org.study.commons.constants.Status;

import java.time.LocalDateTime;
import java.util.List;

//@Entity
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
public class Study{

    @Id
    private Long studyCode; //스터디코드

    @Column(nullable = false, length = 150)
    private String studyNm; //스터디명

    @Column(nullable = false)
    private String category; //카테고리

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime requestDt;//개설신청일시

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status approveStatus = Status.APPLY ; //승인상태 (기본은 승인대기==신청)

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime regStatusDt;//상태처리일시

    private Long maxMember; //신청최대인원수 // 0 - 무제한, 숫자 - 제한

    private Long remainSeat = 0L; //남은 자리수

    private boolean activeStatus; //모집상태(모집중 - true, 모집마감(자리없음) -false)

    private String numOfWeek; //스터디 주당횟수(라디오로 선택된 값 그대로 반영)

    @Enumerated(EnumType.STRING)
    @Column(length=30, nullable=false)
    private  RegionType regionType = RegionType.ONLINE; //지역(온라인/ 오프라인 -추후 프로그램내에서 시/도/군/구 값도 추가되도록 진행예정 )

    @Column(length = 150)
    private String simpleIntro; //한줄 소개글

    @Lob
    @Column(nullable = false)
    private String introduction; //소개글

    // 파일 첨부에 대한 필드도 작성해야 할 것 같습니다.

    @ManyToOne
    @JoinColumn(name="user_no")
    @ToString.Exclude
    private User user;  //개설회원 정보




}
