package org.study.models.cs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.study.commons.validators.BadRequestException;
import org.study.commons.validators.RequiredCheckValidator;
import org.study.commons.validators.ServiceValidator;
import org.study.controllers.admin.cs.QuestionConfig;
import org.study.repositories.cs.QuestionRepository;

@Component
public class QuestionValidator implements ServiceValidator<QuestionConfig>, RequiredCheckValidator {

    @Autowired
    private QuestionRepository repository;

    @Override
    public void check(QuestionConfig config) {
        /** QuestionConfig = null 경우 예외 발생 */
        nullCheck(config, new BadRequestException("잘못된 접근입니다."));

        /** 필수 항목 체크 */
        requiredCheck(config.getSubject(), new BadRequestException("문의 제목을 입력해주세요."));
        requiredCheck(config.getContent(), new BadRequestException("문의 내용을 입력해주세요."));

    }
}
