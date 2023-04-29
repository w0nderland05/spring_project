package org.study.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.study.commons.constants.ReportStatus;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes={
        @Index(name="idx_user_createdAt", columnList = "createdAt DESC")
})
@EntityListeners(AuditingEntityListener.class)
public class Report extends BaseEntity {

    // 신고 유형 ( 스터디 OR 게시판 )
    @Column(length = 20, nullable = false)
    private String division;

    // 신고 번호
    @Id
    @GeneratedValue
    @Column(length = 8)
    private Long code;

    // 신고 세부 내용
    @Column(length = 50, nullable = false)
    private String detail;

    // 신고 처리 여부
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ReportStatus status = ReportStatus.READY;

    // 신고 처리 내용
    @Column(length = 50, nullable = false)
    private String process;

    //개설회원 정보
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_email")
    @ToString.Exclude
    private User user;
}

