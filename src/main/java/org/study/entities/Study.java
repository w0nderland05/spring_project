package org.study.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.study.commons.constants.RegionType;
import org.study.commons.constants.Status;

import java.time.LocalDateTime;

@Entity
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Study extends BaseEntity {

    @Id
    @GeneratedValue
    private Long studyCode; //스터디코드

    @Column(nullable = false, length = 150)
    private String studyNm; //스터디명

    @Column(nullable = false)
    private String category; //카테고리

    @Column(nullable = false)
    private String userEmail;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime requestDt;//개설신청일시

    @Column(nullable = false)
    private Status approveStatus =Status.APPLY ; //승인상태 (기본은 승인대기==신청)

    @Column(nullable = false)
    private LocalDateTime regStatusDt;//상태처리일시

    private Long maxMember; //신청최대인원수

    private Long numOfWeek; //스터디 주당횟수

    @Enumerated(EnumType.STRING)
    @Column(length=30, nullable=false)
    private  RegionType regionType = RegionType.ONLINE; //지역(온라인/ 오프라인 -추후 프로그램내에서 시/도/군/구 값도 추가되도록 진행예정 )

    @Column(length = 150)
    private String simpleIntro; //한줄 소개글

    @Lob
    @Column(nullable = false)
    private String Introduction; //소개글

    @ManyToOne
    @JoinColumn(name="user_No")
    @ToString.Exclude
    private User user;  //회원 목록 조회할 때 필요할 듯
}
