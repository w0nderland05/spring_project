package org.study.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@MappedSuperclass
public class BaseMemberEntity extends BaseEntity { // 모든 엔티티 공통으로 사용
    @Id
    @GeneratedValue
    private Long IdNo;

    @Email
    private String IdEmail; //아이디(이메일)

    @Column(nullable = false,length=6)
    private String userNm; // 회원명


}
