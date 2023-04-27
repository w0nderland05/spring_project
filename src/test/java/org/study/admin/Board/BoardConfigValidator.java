package org.study.admin.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.study.commons.validators.BadRequestException;
import org.study.commons.validators.RequiredCheckValidator;
import org.study.commons.validators.ServiceValidator;
import org.study.controllers.admin.board.BoardConfig;
import org.study.repositories.board.BoardRepository;

import java.util.Scanner;

/**
 * 게시판 등록 Validator
 *
 */
@Component
public class BoardConfigValidator implements ServiceValidator<BoardConfig>, RequiredCheckValidator {

    @Autowired
    private BoardRepository repository;


    @Override
    public void check(BoardConfig boardConfig) {
        /** BoardConfig가 null 값일 경우 예외 발생 */
        nullCheck(boardConfig, new BadRequestException("잘못된 접근입니다."));

        /** 필수 항목 체크 */
        requiredCheck(boardConfig.getBId(),new BadRequestException("게시판 아이디를 입력하세요."));
        requiredCheck(boardConfig.getBoardNm(),new BadRequestException("게시판 이름을 입력하세요."));
        nullCheck(boardConfig.getRowsPerPage(),new BadRequestException("페이지 당 게시글 수를 입력하세요."));
        requiredCheck(boardConfig.getSkin(),new BadRequestException("적용시킬 스킨을 선택해주세요"));

        /** bId 중복 여부 체크 */
        if (repository.exists(boardConfig.getBId())) {
            // 이미 등록된 게시판 아이디인 경우
            throw new DuplicateCateBIdException();
        }
//
//        /** rowsPerPage : 최소 10개부터 되는지 체크 */
//        if (boardConfig.getRowsPerPage() < 10) {
//            throw new BadRequestException("10보다 큰 값을 입력하세요");
//        }


    }
}
