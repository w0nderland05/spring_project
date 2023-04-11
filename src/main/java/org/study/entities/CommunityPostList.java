package org.study.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes={
        @Index(name="idx_user_createdAt", columnList = "createdAt DESC")
})
<<<<<<< HEAD
@EntityListeners(AuditingEntityListener.class)

=======

// 게시글 목록 Entity
>>>>>>> 9ecc3a30646507443778b8a9a4cae6aefaa7e126
public class CommunityPostList extends BaseEntity {

    @Id @GeneratedValue
    @Column(length = 8)
    private Long code; // 코드번호

    @Column(nullable = false)
    private String category; // 카테고리

    @Column(nullable = false, length = 150)
    private String postNm; // 게시글 제목

    // 작성자명과 이메일은 매핑으로 가져옴.
    // 작성일시와 수정일시는 BaseEntity

    @Column(columnDefinition = "int default '0' not null")
    private int viewCount; // 조회수

    @ManyToOne
    @JoinColumn(name = "user_No")
    @ToString.Exclude
    private User user;

}
