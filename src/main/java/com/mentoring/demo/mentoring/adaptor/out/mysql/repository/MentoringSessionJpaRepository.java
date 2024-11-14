package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface MentoringSessionJpaRepository extends JpaRepository<MentoringSessionEntity,Long> {
    MentoringSessionEntity findByUuid(String uuid);

    // uuid로 isClosed 컬럼 true로 업데이트
    @Modifying
    @Transactional
    //@Query("UPDATE MentoringSessionEntity s SET s.isClosed = true WHERE s.uuid = :uuid")
    @Query("UPDATE mentoring_session s SET s.isClosed = true WHERE s.uuid = :uuid")
    void updateIsClosedByUuid(@Param("uuid") String uuid);


    @Query(value = "SELECT MS.*" +
                    "FROM mentoring_session MS " +
                    "JOIN mentoring MT ON MS.mentoring_id = MT.id " +
                    "AND MT.mentor_uuid = :mentorUuid " +
                    "WHERE MS.is_deleted = false " +
                    "AND ( " +
                             "(MS.start_date + INTERVAL TIME_TO_SEC(MS.start_time) SECOND < :endDate + INTERVAL TIME_TO_SEC(:endTime) SECOND) " +
                             "AND " +
                             "(MS.end_date + INTERVAL TIME_TO_SEC(MS.end_time) SECOND > :startDate + INTERVAL TIME_TO_SEC(:startTime) SECOND) " +
                       ") " +
                    "ORDER BY MS.start_date ASC, MS.start_time ASC " +
                    "LIMIT 1",
                    nativeQuery = true)
    Optional<MentoringSessionEntity> findOneValidSessionTime(@Param("startDate") LocalDate startDate,
                                                             @Param("startTime") LocalTime startTime,
                                                             @Param("endDate") LocalDate endDate,
                                                             @Param("endTime") LocalTime endTime,
                                                             @Param("mentorUuid") String mentorUuid);

}
