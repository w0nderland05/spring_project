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
import org.study.entities.User;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionConfig {

    private Long qsCode; // 문의 코드

    @NotBlank(message = "제목을 입력하세요.")
    private String subject; // 문의 제목

    @NotBlank(message = "내용을 입력하세요.")
    private String content; // 문의 내용


    private String csProcess; // 문의 처리 상태


    private String category; // 문의 유형

    private User user;

    public static Question of (QuestionConfig questionConfig) {
        return new ModelMapper().map(questionConfig, Question.class);
    }
}
