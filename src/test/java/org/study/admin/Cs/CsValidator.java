package org.study.admin.Cs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.study.commons.validators.BadRequestException;
import org.study.commons.validators.ReportNotFoundException;
import org.study.commons.validators.RequiredCheckValidator;
import org.study.commons.validators.ServiceValidator;
import org.study.repositories.ReportRepository;

@Component
public class CsValidator implements ServiceValidator<CsConfig>, RequiredCheckValidator {

    @Autowired
    private ReportRepository repository;

    @Override
    public void check(CsConfig config) {
        /** CsConfig가 null 값일 경우 예외 발생 */
        nullCheck(config, new BadRequestException("잘못된 접근입니다."));

        /** 필수 항목 체크 */
        nullCheck(config.getCode(), new BadRequestException(""));
        // DB에서 들어오는 코드도 들어가야하나욤? 메세지 어떻게 써야하나용

        nullCheck(config.getDivision(), new BadRequestException("신고 유형을 선택해주세요"));
        nullCheck(config.getDetail(), new BadRequestException("신고 세부 내용을 입력해주세요."));
        nullCheck(config.getStatus(), new BadRequestException("신고 처리 여부를 선택해주세요."));
        nullCheck(config.getProcess(), new BadRequestException("신고 처리 내용을 입력해주세요."));
    }


}
