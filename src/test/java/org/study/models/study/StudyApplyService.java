package org.study.models.study;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.repositories.StudyRepository;

@Service
@RequiredArgsConstructor
public class StudyApplyService {

    private final StudyRepository repository;

    public void apply() {


    }
}
