package org.study.controllers.user.Community;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.study.entities.User;
import org.study.entities.board.BoardData;

/**
 * 게시글 커맨드 객체
 */
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class PostConfig {

    private String mode;
    @NotNull
    private Long id; // 게시글 번호

    @NotNull
    private Long gid; // 그룹 아이디

//    private String poster; // 작성자
//
//    private String guestPw; // 비회원 비밀번호
//
//    private boolean isNotice; // 공지사항 여부
//
    @NotBlank
    private String category; // 게시글 분류

    @NotBlank
    private String subject; // 게시글 제목

    @NotBlank
    private String content; // 게시글 내용

    private String createdAt, modifiedAt;

//    private Long idParent; // 답글인 경우 부모 게시글 번호
//
//    private String ipAddr; // IP 주소
//
//    private String userAgent; // 브라우저 정보
//
//    private boolean useEditor; // 에디터 사용 여부
//
//    private Integer totalComments; // 댓글 총 갯수
//
//    private Integer depth; // 답글 작성시 들여쓰기 정도
//
//    private Integer reviewPt; // 후기 평점
//
//    private Long listOrder; // 게시글 1차 정렬 - 내림차순
//
//    private Long listOrder2; // 게시글 2차 정렬  - 내림차순

    private int page = 1; // 페이지 번호

    private int limit = 20; // 1페이지당 출력 갯수

    private User user;

    public static BoardData of (PostConfig postConfig) { return new ModelMapper().map(postConfig, BoardData.class); }
}
