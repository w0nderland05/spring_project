package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.QStudies;
import org.study.entities.Studies;


<<<<<<< HEAD
    /**
     * 스터디 코드 등록 여부 체크
     *
     * @param studyCd
     * @return
     */
    default boolean exists(String studyCd) {
        QStudies studies = QStudies.studies;

        return exists(studies.studyCd.eq(studyCd));
    }
}
=======
public interface StudiesRepository extends JpaRepository<Studies, Long>, QuerydslPredicateExecutor<Studies> {
/**
 * 스터디 코드 등록 여부 체크
 *
 * @param studyCd
 * @return
 */
default boolean exists(String studyCd){
        QStudies studies=QStudies.studies;

        return exists(studies.studyCd.eq(studyCd));
           }
        }
>>>>>>> 8599cd278c32cbb189dc5157662e7182b7915bbe
