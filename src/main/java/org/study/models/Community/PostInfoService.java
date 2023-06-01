package org.study.models.Community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.commons.validators.PostNotFoundException;
import org.study.controllers.user.Community.PostConfig;
import org.study.entities.board.BoardData;
import org.study.repositories.board.BoardDataRepository;

@Service
public class PostInfoService {
    @Autowired
    private BoardDataRepository boardDataRepository;

    public PostConfig get(Long gid) {
        if (gid == null) {
            throw new PostNotFoundException();
        }

        BoardData boardData = boardDataRepository.findById(gid).orElseThrow(PostNotFoundException::new);

        PostConfig postConfig = PostConfig.builder()
                .id(boardData.getId())
                .gid(boardData.getGid())
                .category(boardData.getCategory())
                .subject(boardData.getSubject())
                .content(boardData.getContent())
                .build();

        return postConfig;
    }
}
