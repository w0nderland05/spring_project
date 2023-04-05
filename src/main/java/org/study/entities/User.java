package org.study.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.study.commons.constants.UserRole;

/**
 * 회원 엔티티
 *
 */
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes={ // 이메일 및 사용자 역할은 조회가 많이 될 수 있으므로 인덱스 부여
            // 관리자 페이지에서 최신 회원순으로 조회를 많이 할 수 있으므로 인덱스 부여
            @Index(name="id`x_user_email", columnList = "email"),
            @Index(name="idx_user_role", columnList = "role"),
            @Index(name="idx_user_createdAt", columnList = "createdAt DESC")
})
public class User extends BaseEntity {
    @Id @GeneratedValue
    private Long userNo;

    @Column(unique=true, nullable=false, length=45)
    private String userId;

    @Column(nullable=false, length=65)
    private String userPw;

    @Column(nullable=false, length=45)
    private String userNm;

    @Column(nullable=false)
    private String email; // 이메일은 비밀번호 찾기시 초기화 메일 전송으로 사용되므로 필수

    @Column(length=11)
    private String cellPhone;
    @Lob
    private String adminMemo; // 관리자 메모

    @Enumerated(EnumType.STRING)
    @Column(length=30, nullable=false)
    private UserRole role = UserRole.USER; // 사용자 역할, 기본값은 USER(일반사용자)
}
