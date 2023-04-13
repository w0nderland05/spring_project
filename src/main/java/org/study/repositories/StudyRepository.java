package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.entities.Study;

public interface StudyRepository extends JpaRepository<Study,Long> {

<<<<<<< HEAD
    Study findByStudyCode(Long StudyCode);


=======
>>>>>>> 5fd627507ace88f9ef256ba89f5f60c755d41bd9
}
