package org.study.models.studies;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.study.controllers.study.StudySearch;
import org.study.entities.Studies;

@Service
public class StudyListService {
    public Page<Studies> gets(StudySearch search) {
        return null;
    }
}
