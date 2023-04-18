package org.study.controllers.admin.board;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 게시판 등록은 관리자의 권한으로만 가능
 */
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class BoardConfig {

    private String mode; // mode가 update면 수정

    @NotBlank(message = "게시판 아이디를 입력하세요")
    private String bId;

    @NotBlank(message="게시판명을 입력하세요")
    private String boardNm;

    private boolean isUse;

    private Long rowsPerPage; // 1페이지에 노출될 게시글 수
    // 숫자를 입력 혹은 선택 안할 시 메세지 출력 ??

    private boolean useViewList; // 게시글 하단에 목록 노출

    private String category; // 분류
    // 분류는 안적어도 메세지 출력 안해도 되는지 ??

    private String viewType; // 출력구분 (관리자, 회원)

    private boolean useEditor; // 에디터 사용여부

    private List<String> useFileAttach; // 파일 첨부 사용여부

    private List<String> useImageAttach; // 이미지 첨부(에디터 추가) 사용여부

    private String afterWriteTarget; // 글 작성 후 이동 경로

    private boolean useComment; // 댓글 사용여부

    private String skin; // 스킨명
    // 스킨 선택 안할 시 메세지 출력 ??

    private boolean isReview; // 후기 게시판 여부


}
