package org.study.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class StudyCategory {

    @Id
    @GeneratedValue
    private Long scNo;//스터디카테고리 넘버

    @Column(nullable = false)
    private String scNm; //스터디카테고리명

    @Column
    private Boolean scUse; //스터디카테고리 사용여부


}
