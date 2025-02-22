package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MentoringCategoryJpaRepository extends JpaRepository<MentoringCategoryEntity,Long> {
    @Modifying
    void deleteByMentoringUuid(String mentoringId);

    @Modifying
    @Query("DELETE FROM mentoring_category_list m WHERE m.mentoringEntity.id = :mentoringId")
    void deleteByMentoringEntityId(@Param("mentoringId") Long mentoringId);
}
