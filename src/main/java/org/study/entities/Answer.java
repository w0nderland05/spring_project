package org.study.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends BaseEntity {

//    @GeneratedValue
    @Id
    @Column(length = 8, nullable = false)
    private Long asCode; // 답변 코드

    @Column(length = 300)
    private String content; // 답변 내용

    //개설회원 정보
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_email")
    @ToString.Exclude
    private User user;
    
    @ManyToOne
    private Question question; // 답변 엔티티에 질문 엔티티 참조


}
