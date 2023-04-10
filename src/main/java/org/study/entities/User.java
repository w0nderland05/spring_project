package org.study.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.study.commons.constants.UserRole;

import java.util.ArrayList;
import java.util.List;
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
        @Index(name="idx_user_email", columnList = "userEmail"),
        @Index(name="idx_user_role", columnList = "role"),
        @Index(name="idx_user_createdAt", columnList = "createdAt DESC")
})
public class User extends BaseEntity{
    @Id
    @GeneratedValue
    private Long userNo;

    @Column(unique=true, nullable=false, length=45)
    private String userEmail; //아이디(이메일 )

    @Column(nullable = false,length=35)
    private String userNm; // 회원명

    @Column(nullable = false, length=40)
    private String userNickNm; // 닉네임

    @Column(nullable=false, length=65)
    private String userPw;

    @Column(length=11)
    private String cellPhone;

    @Enumerated(EnumType.STRING)
    @Column(length=30, nullable=false)
    private UserRole role = UserRole.USER; // 사용자 역할, 기본값은 USER(일반사용자)

    @OneToMany(mappedBy="user")
    private List<Study> study = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CommunityPostList> communityPostLists = new ArrayList<>();
}
