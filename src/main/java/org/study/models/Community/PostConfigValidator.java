package org.study.models.Community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.study.commons.validators.BadRequestException;
import org.study.commons.validators.RequiredCheckValidator;
import org.study.commons.validators.ServiceValidator;
import org.study.controllers.user.Community.PostConfig;
import org.study.repositories.board.BoardDataRepository;

/**
 * 게시글 등록 Validator
 */
@Component
public class PostConfigValidator implements ServiceValidator<PostConfig>, RequiredCheckValidator {
    @Autowired
    private BoardDataRepository repository;


    @Override
    public void check(PostConfig postConfig) {
        /** PostConfig가 null 값일 경우 예외 발생 */
        nullCheck(postConfig, new BadRequestException("잘못된 접근입니다."));

        /** 필수항목 체크 */
        nullCheck(postConfig.getGid(), new BadRequestException("그룹 아이디를 입력하세요."));
        requiredCheck(postConfig.getSubject(), new BadRequestException("제목을 입력하세요."));
        requiredCheck(postConfig.getContent(), new BadRequestException("내용을 입력하세요."));

        /** gid 중복 여부 체크 */
        if (repository.exists(postConfig.getGid())) {
            // 이미 등록된 gid인 경우
            throw new BadRequestException()
        }

    }
}
