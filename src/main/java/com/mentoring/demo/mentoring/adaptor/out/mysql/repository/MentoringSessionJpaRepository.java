package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoringSessionJpaRepository extends JpaRepository<MentoringSessionEntity,Long> {

}
