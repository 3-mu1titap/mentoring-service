package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MentoringJpaRepository extends JpaRepository<MentoringEntity,Long> {
    MentoringEntity findByMentoringUuid(String mentoringUuid);

    @Query("SELECT m.id FROM mentoring m WHERE m.mentoringUuid = :mentoringUuid")
    String getIdByMentoringUuid(@Param("mentoringUuid") String mentoringUuid);



}
