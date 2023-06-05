package org.study.controllers.admin.cs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.study.entities.Answer;

public class AnswerConfig {

    @NotNull
    private Long asCode; // 답변 코드

    @NotBlank
    private String content; // 답변 내용

    public static Answer of (AnswerConfig answerConfig) {
        return new ModelMapper().map(answerConfig, Answer.class);
    }
}
