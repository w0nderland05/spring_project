package org.study.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes={
        @Index(name="idx_user_createdAt", columnList = "createdAt DESC")
})
@EntityListeners(AuditingEntityListener.class)
public class CommunityPostList extends BaseEntity {

    @Id @GeneratedValue
    private Long code; // 코드번호

    @Column(nullable = false)
    private String category; // 카테고리

    @Column(nullable = false, length = 150)
    private String postNm; // 게시글 제목

    // 작성일시와 수정일시는 BaseEntity

    @Column(columnDefinition = "int default '0' not null")
    private int viewCount; // 조회수

    @ManyToOne
    @JoinColumn(name = "userNo")
    private User user;

}