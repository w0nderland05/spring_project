package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.entities.Study;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study,Long> {


    Study findByStudyCode(Long StudyCode);

<<<<<<< HEAD
    //  UserNm 만 가져오려면
    //List<UserMapping> findByEmail();




=======
    
>>>>>>> 2aa59e21d66c1993d0bd0fcfeeb33aeac3aa71be
}
