package org.study.models.cs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.commons.validators.QuestionNotFoundException;
import org.study.entities.Question;
import org.study.repositories.cs.QuestionRepository;
import org.study.repositories.cs.ReportRepository;
import org.study.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CsReportService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // 질문 목록 조회
    public List<Question> getList() {

        return questionRepository.findAll();
    }

    public Question getQuestion(Long qsCode) {
        Optional<Question> question = this.questionRepository.findById(qsCode);
        if(question.isPresent()) {
            return question.get();
        } else {
            throw new QuestionNotFoundException();
        }
    }

}
