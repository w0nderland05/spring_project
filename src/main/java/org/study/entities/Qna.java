package org.study.entities;

import jakarta.persistence.*;
import lombok.*;
import org.study.commons.constants.QnaCategory;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Qna extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(length = 8)
    private Long qnaNo; // 문의 번호

    private boolean qStatus; // 처리 상태

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private QnaCategory category; // 문의 유형 ( ENUM )

    @Column(nullable=false, length=45)
    private String userNm;

    @Column(nullable=false)
    private String email; // 이메일은 비밀번호 찾기시 초기화 메일 전송으로 사용되므로 필수

    // 문의 일시, 답변 일시 BaseEntity 사용

}
