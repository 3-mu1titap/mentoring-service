package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface MentoringCategoryJpaRepository extends JpaRepository<MentoringCategoryEntity,Long> {
    @Modifying
    void deleteByMentoringUuid(String mentoringUuid);
}
