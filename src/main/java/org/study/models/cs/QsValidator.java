package org.study.models.cs;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.study.controllers.admin.cs.QuestionConfig;

/** 쓸지 고민중인 클래스입니당 */ 
@Configuration
public class QsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return QuestionConfig.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        QuestionConfig qsConfig = (QuestionConfig) target;

        String subject = qsConfig.getSubject();
        String content = qsConfig.getContent();

        if (subject == null || subject.isBlank()){
            errors.rejectValue("subject",  "제목을 입력하세요.");
        }

        if (content == null || content.isBlank()){
            errors.reject("content","내용을 입력하세요.");
        }

    }
}
