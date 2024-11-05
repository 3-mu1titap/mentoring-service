package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface MentoringJpaRepository extends JpaRepository<MentoringEntity,Long> {
    MentoringEntity findByMentoringUuid(String mentoringUuid);



}
