package org.study.controllers.admin.cs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.study.commons.constants.ReportStatus;
import org.study.entities.Question;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionConfig {

    @NotNull
    private Long qsCode; // 문의 코드

    @NotBlank
    private String subject; // 문의 제목

    @NotBlank
    private String content; // 문의 내용

    @NotBlank
    private String csProcess; // 문의 처리 상태

    @NotBlank
    private String category; // 문의 종류

    public static Question of (QuestionConfig questionConfig) {
        return new ModelMapper().map(questionConfig, Question.class);
    }
}
