package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.DeadlinePastSessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeDtoByDateDto;
import com.mentoring.demo.mentoring.domain.vo.TimeRange;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface MentoringSessionDslRepository {
    Map<LocalDate, List<TimeRange>>  getSessionTimeMapUntilDeadline(String mentoringId, LocalDate deadLineDate);

    List<DeadlinePastSessionResponseOutDto> findPastDeadlineSessions(LocalDate now);
    boolean existsMentoringSession(String mentoringId, MentoringSessionOutDto mentoringSessionOutDto);
}
