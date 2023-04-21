package org.study.admin.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.controllers.admin.board.BoardConfig;
import org.study.entities.board.Board;
import org.study.repositories.board.BoardRepository;

@Service
public class BoardConfigService {
    @Autowired
    private BoardRepository repository;

//    public void config() {
//        BoardConfig boardConfig = BoardConfig.builder()
//                .bId();
//    }
}
