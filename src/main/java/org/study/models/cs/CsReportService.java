package org.study.models.cs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.commons.validators.QuestionNotFoundException;
import org.study.controllers.admin.cs.QuestionConfig;
import org.study.entities.Question;
import org.study.repositories.cs.QuestionRepository;
import org.study.repositories.cs.ReportRepository;
import org.study.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
public class CsReportService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    private QuestionConfig toConfig(Question question) {
        return QuestionConfig.builder()
                .subject(question.getSubject())
                .content(question.getContent())
//                .csProcess(question.getCsProcess().toString())
                .category(question.getCategory().toString())
                .build();
    }

    // 질문 목록 전체 조회
    public List<QuestionConfig> getList() {
        List<Question> questions = questionRepository.findAll(Sort.by(desc("createdAt")));
        if(questions == null && questions.isEmpty()) {
            throw new QuestionNotFoundException();
        }
        List<QuestionConfig> questionList = questions.stream().map(this::toConfig).toList();
        return questionList;
    }

    // 하나의 질문 조회
    public QuestionConfig getQuestion(Long qsCode) {
        /*Optional<Question> question = questionRepository.findById(qsCode);
        if(question.isPresent()) {
            return question.get();
        } else {
            throw new QuestionNotFoundException();
        }*/
        if(qsCode == null) {
            throw new QuestionNotFoundException();
        }

        Question question = questionRepository.findById(qsCode).orElseThrow(QuestionNotFoundException::new);

        QuestionConfig config = QuestionConfig.builder()
//                .qsCode(question.getQsCode())
                .subject(question.getSubject())
                .content(question.getContent())
//                .csProcess(question.getCsProcess().toString())
                .category(question.getCategory().toString())
                .build();

        return config;
    }
}