package org.study.models.cs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.controllers.admin.cs.QuestionConfig;
import org.study.entities.Question;
import org.study.repositories.cs.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionListService {

    private final QuestionRepository repository;

    public Question get(Long qsCode) {
        Question question = repository.findById(qsCode).orElseThrow(DuplicateQsCodeException::new);

        return question;
    }

}
