package org.study.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 범용 분류 entity
 */
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(indexes={
        @Index(name="idx_listOrder_desc", columnList="listOrder DESC"),
        @Index(name="created_at_desc", columnList="createdAt DESC")
})
public class Category extends BaseEntity {

    @Id @Column(length=20)
    private String cateCd; // 분류 코드

    @Column(name="is_use")
    private boolean use; // 사용 여부

    @Column(length=30, nullable = false)
    private String location; // 노출 위치

    @Column(length=60, nullable=false)
    private String cateNm; // 분류명
    private Long listOrder; // 진열 순서 - 숫자가 높을수록 진열 우선 가중치 부여

    @Column(length=20)
    private String parentCateCd; // 하위 분류인 경우는 부모 분류코드가 있
}
