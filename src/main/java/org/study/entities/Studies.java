package org.study.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.study.commons.constants.RegionType;
import org.study.commons.constants.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Studies extends BaseEntity {
    @Id @GeneratedValue
    private Long studyNo; // 스터디 등록번호
    @Column(length=45, nullable = false)
    private String gid = UUID.randomUUID().toString();

    @Column(nullable=false, length=30, unique=true)
    private String StudyCd; // 사용자 등록 스터디 코드

    @Column(nullable=false, length=60)
    private String cateCd; // 스터디 분류

    //승인상태 (기본은 승인대기==신청)
    @Enumerated(EnumType.STRING)
    @Column(length=30, nullable=false)
    private Status status = Status.APPLY;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime statusDt; // 상태 변경 일시

    private long maxMember; // 신청최대인원 0 - 무제한, 숫자 - 제한

    private int numOfWeek = 1; // //스터디 주당횟수(라디오로 선택된 값 그대로 반영)

    private RegionType regionType = RegionType.ONLINE;

    @Column(length = 150)
    private String simpleIntro; //한줄 소개글

    @Lob
    private String introduction; //소개글

    @ManyToOne
    @JoinColumn(name="userNo")
    private User user;
}
