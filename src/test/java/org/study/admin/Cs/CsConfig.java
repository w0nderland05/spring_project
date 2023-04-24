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
    
    private String division;

    private Long code;

    private String detail;

    private ReportStatus status = ReportStatus.READY;

    private String process;




}
