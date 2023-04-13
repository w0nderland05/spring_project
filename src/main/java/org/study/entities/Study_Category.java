package org.study.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Study_Category {

    @Id
    @GeneratedValue
    private Long CategoryNo;

    @Column(nullable = false)
    private String Category;

    @Column
    private Boolean isUse;


}
