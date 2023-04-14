package org.study.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class StudyCategory extends BaseEntity{

    @Id
    @Column(nullable = false)
    private String scId;

    @Column(nullable = false)
    private String scNm; //스터디 카테고리 명

    private  boolean scUse; //스터디카테고리사용여부
=======

@Entity
public class StudyCategory {

    @Id
    @GeneratedValue
    private Long scNo;//스터디카테고리 넘버

    @Column(nullable = false)
    private String scNm; //스터디카테고리명

    @Column
    private Boolean scUse; //스터디카테고리 사용여부


>>>>>>> 2aa59e21d66c1993d0bd0fcfeeb33aeac3aa71be
}
