package org.study.admin.Cs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.study.commons.constants.ReportStatus;
import org.study.controllers.admin.board.BoardConfig;
import org.study.entities.Report;
import org.study.entities.board.Board;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class CsConfig {

    // 신고 유형 ( 스터디 OR 게시판 )
    @NotBlank
    private String division;

    // 회원 번호
    private Long code;

    // 신고 세부 내용
    @NotBlank
    private String detail;

    // 신고 처리 여부 ( select 옵션 선택 ) , ? 기본값 = 처리 대기중
    @NotBlank
    private String status;

    // 신고 처리 내용
    @NotBlank
    private String process;

    public static Report of (CsConfig csConfig) {
        return new ModelMapper().map(csConfig, Report.class);
    }


}
