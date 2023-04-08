package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.entities.Study;

public interface StudyRepository extends JpaRepository<Study,Long> {

    Study findByStudyCode(Long StudyCode);
}
