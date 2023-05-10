package org.study.models.Community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.controllers.user.Community.PostConfig;
import org.study.entities.User;
import org.study.entities.board.BoardData;
import org.study.repositories.board.BoardDataRepository;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

/**
 * 게시글 목록 조회 서비스
 */

@Service
public class PostListService {

    @Autowired
    private BoardDataRepository boardDataRepository;

    public List<PostConfig> gets() {
        List<BoardData> datas = boardDataRepository.findAll(Sort.by(Sort.Order.desc("createdAt")));
        List<PostConfig> postConfigs = datas.stream().map(this::toConfig).toList();

        return postConfigs;
    }

    private PostConfig toConfig(BoardData boardData) {
        return PostConfig.builder()
                .gid(boardData.getGid())
                .category(boardData.getCategory())
                .subject(boardData.getSubject())
                .poster(boardData.getPoster())
                .build();
    }
}
