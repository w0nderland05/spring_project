package org.study.admin.Cs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.study.commons.constants.ReportStatus;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class CsConfig {

    // 신고 유형 ( 스터디 OR 게시판 )
    private String division;

    // 회원 번호
    private Long code;

    // 신고 세부 내용
    private String detail;

    // 신고 처리 여부 ( select 옵션 선택 ) , 기본값 = 처리 대기중
    private ReportStatus status = ReportStatus.READY;

    // 신고 처리 내용
    @NotBlank(message = "신고 처리 내용을 입력해주세요")
    private String process;




}
