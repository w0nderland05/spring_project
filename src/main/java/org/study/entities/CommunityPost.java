//package org.study.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//@Entity
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(indexes={
//        @Index(name="idx_user_createdAt", columnList = "createdAt DESC")
//})
//<<<<<<< HEAD
//
//@EntityListeners(AuditingEntityListener.class)
//
//
//
//// 게시글 목록 Entity
//
//=======
//@EntityListeners(AuditingEntityListener.class)
//// 게시글 목록 Entity
//<<<<<<< HEAD:src/main/java/org/study/entities/CommunityPostList.java
//>>>>>>> 5fd627507ace88f9ef256ba89f5f60c755d41bd9
//public class CommunityPostList extends BaseEntity {
//=======
//public class CommunityPost extends BaseEntity {
//>>>>>>> eea60d6bed283fe24b03f38bf3bf37ac492bc9e7:src/main/java/org/study/entities/CommunityPost.java
//
//    @Id @GeneratedValue
//    @Column(length = 8)
//    private Long code; // 코드번호
//
//    @Column(nullable = false, length = 150)
//    private String postNm; // 게시글 제목
//
//    // 작성자명과 이메일은 매핑으로 가져옴.
//    // 작성일시와 수정일시는 BaseEntity
//
//    @Column(columnDefinition = "int default '0' not null")
//    private int viewCount; // 조회수
//
//    @ManyToOne
//    @JoinColumn(name = "user_No")
//    @ToString.Exclude
//    private User user;
//
//    @OneToOne
//    @JoinColumn(name = "boardNm")
//    private CommunityBoard category;
//}
