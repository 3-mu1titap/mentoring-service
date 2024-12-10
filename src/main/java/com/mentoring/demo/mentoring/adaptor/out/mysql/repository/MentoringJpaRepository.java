package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MentoringJpaRepository extends JpaRepository<MentoringEntity,Long> {
    MentoringEntity findByMentoringUuid(String mentoringUuid);

    @Query("SELECT m.id FROM mentoring m WHERE m.mentoringUuid = :mentoringUuid")
    String getIdByMentoringUuid(@Param("mentoringUuid") String mentoringUuid);

    // 모든 멘토링 uui 가져오는 메서드
    @Query("SELECT m.mentoringUuid FROM mentoring m WHERE m.isDeleted = false")
    List<String> findAllMentoringUuids();

    // 특정 멘토링 UUID에 해당하는 세션 UUID 리스트를 가져오는 메서드
    @Query("SELECT ms.uuid FROM mentoring_session ms WHERE ms.mentoringEntity.mentoringUuid = :mentoringUuid AND ms.isDeleted = false")
    List<String> findSessionUuidsByMentoringUuid(@Param("mentoringUuid") String mentoringUuid);

}
