package org.study.entities.board;

import jakarta.persistence.*;
import lombok.*;
import org.study.entities.BaseEntity;
import org.study.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시글 데이터
 *
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardData extends BaseEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; // 게시글 번호

    @Column(length=45, nullable = false)
    private Long gid; // 그룹 아이디

    @Column(length=45, nullable = false)
    private String poster; // 작성자

    @Column(length=65)
    private String guestPw; // 비회원 비밀번호

    private boolean isNotice; // 공지사항 여부

    @Column(length=60)
    private String category; // 게시글 분류
    @Column(nullable = false)
    private String subject; // 게시글 제목

    @Lob
    @Column(nullable = false)
    private String content; // 게시글 내용

    private Long idParent; // 답글인 경우 부모 게시글 번호

    private Integer viewCnt; // 조회수

    @Column(length=30)
    private String ipAddr; // IP 주소

    private String userAgent; // 브라우저 정보(UserAgent)

    private boolean useEditor; // 에디터 사용여부

    private Integer totalComments; // 댓글 총 갯수

    private Integer depth; // 답글 작성시 들여쓰기 정도

    private Integer reviewPt; // 후기 평점
    
    private Long listOrder; // 게시글 1차 정렬 - 내림차순
    private Long listOrder2; // 게시글 2차 정렬  - 내림차순

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userNo")
    private User user; // 글 작성자

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bId")
    private Board board; // 게시판 설정

    @ToString.Exclude
    @OneToMany(mappedBy="boardData", fetch=FetchType.LAZY)
    private List<BoardComment> comments = new ArrayList<>(); // 댓글 조회

}
