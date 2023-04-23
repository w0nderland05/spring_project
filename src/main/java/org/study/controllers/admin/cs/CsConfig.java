package org.study.controllers.admin.cs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.study.commons.constants.ReportStatus;
import org.study.entities.User;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class CsConfig {

    private String division;

    private Long code;

    private String detail;

    private String process;


}
