package org.study.models.Community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.controllers.admin.community.CommunitySearch;
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

    public List<BoardData> getAll() { return gets(null, 0, 0); }

    public List<BoardData> gets(CommunitySearch search, int page, int limit) {
        Sort sort = Sort.by(desc("listOrder"), desc("createdAt")); // 기본 정렬은 최신순으로
        List<BoardData> datas = boardDataRepository.findAll(sort);

        return datas;
    }
}
