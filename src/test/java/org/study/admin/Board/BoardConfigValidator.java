package org.study.admin.Board;

import org.springframework.stereotype.Component;
import org.study.commons.validators.BadRequestException;
import org.study.commons.validators.RequiredCheckValidator;
import org.study.commons.validators.ServiceValidator;
import org.study.controllers.admin.board.BoardConfig;

/**
 * 게시판 등록 Validator
 *
 */
@Component
public class BoardConfigValidator implements ServiceValidator<BoardConfig>, RequiredCheckValidator {

    @Override
    public void check(BoardConfig boardConfig) {
        /** BoardConfig가 null 값일 경우 예외 발생 */
        nullCheck(boardConfig, new BadRequestException("잘못된 접근입니다."));

        /** 필수 항목 체크 */
        requiredCheck(boardConfig.getBId(),new BadRequestException("게시판 아이디를 입력하세요."));
        requiredCheck(boardConfig.getBoardNm(),new BadRequestException("게시판 이름을 입력하세요."));
        requiredCheck(boardConfig.getRowsPerPage().toString(),new BadRequestException("페이지 당 게시글 수를 입력하세요."));
        requiredCheck(boardConfig.getSkin(),new BadRequestException("적용시킬 스킨을 선택해주세요"));
    }
}
