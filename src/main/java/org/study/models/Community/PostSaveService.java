package org.study.models.Community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.study.commons.UserUtils;
import org.study.controllers.user.Community.PostConfig;
import org.study.entities.board.BoardData;
import org.study.repositories.UserRepository;
import org.study.repositories.board.BoardDataRepository;

@Service
public class PostSaveService {
    @Autowired
    private BoardDataRepository dataRepository;
    @Autowired
    private PostSaveValidator postSaveValidator;
    @Autowired
    private UserUtils userUtils;
    @Autowired
    private UserRepository userRepository;

    /**
     * 컨트롤러 Bean Validation 대응
     * @param postConfig
     */
    public void postConfig(PostConfig postConfig) {
        postConfig(postConfig, null);
    }

    public void postConfig(PostConfig postConfig, Errors errors) {
        /** 로그인 하지 않은 경우 작성 불가 처리 */
        if (!userUtils.isLogin() || errors != null && errors.hasErrors()) {
            return;
        }
        postSaveValidator.check(postConfig, errors);

        /**
         * 엔티티가 이미 등록된 gid라면 기본 엔티티를 가져오고 ( 수정 )
         * 없다면 새로운 엔티티로 변환 PostConfig.if(postConfig)
         */

        BoardData boardData = null;
        Long gid = postConfig.getGid();
        String mode = postConfig.getMode();
        if (mode != null && mode.equals("update") && dataRepository.exists(gid)) {
            boardData = dataRepository.findById(gid).orElseGet(() -> postConfig.of(postConfig));
        } else {
            boardData.setUser(userUtils.getEntity());
            boardData = PostConfig.of(postConfig);
//            boardData = new BoardData();
//            boardData.setGid(postConfig.getGid());
//            boardData.setSubject(postConfig.getSubject());
//            boardData.setContent(postConfig.getContent());
        }

        // 엔티티 저장 또는 수정 처리
        dataRepository.saveAndFlush(boardData);
    }
}
