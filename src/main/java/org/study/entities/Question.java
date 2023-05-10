package org.study.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.study.commons.constants.QnaCategory;
import org.study.commons.constants.ReportStatus;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes={
        @Index(name="idx_user_createdAt", columnList = "createdAt DESC")
})
public class Question extends BaseEntity {


//    @GeneratedValue
    @Id
    @Column(length = 8, nullable = false)
    private Long qsCode; // 문의 코드

    @Column(length = 100)
    private String subject; // 문의 제목

    @Column(length = 300)
    private String content; // 문의 내용

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus csProcess; // 문의 처리 상태

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private QnaCategory category; // 문의 종류

    //개설회원 정보
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_email")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList; // 질문 엔티티에 답변 엔티티 참조
}
