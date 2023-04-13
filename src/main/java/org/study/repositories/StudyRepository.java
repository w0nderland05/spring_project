package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.entities.Study;

public interface StudyRepository extends JpaRepository<Study,Long> {

<<<<<<< HEAD

    Study findByStudyCode(Long StudyCode);


=======
    Study findByStudyCode(Long StudyCode);



>>>>>>> f8193567d14903e854600291d71d39e58367873e
}
