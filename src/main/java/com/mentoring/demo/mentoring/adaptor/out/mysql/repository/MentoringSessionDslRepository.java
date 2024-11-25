package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeDtoByDateDto;
import com.mentoring.demo.mentoring.domain.vo.TimeRange;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface MentoringSessionDslRepository {
    Map<LocalDate, List<TimeRange>>  getSessionTimeUntilDeadline(String mentoringId, LocalDate deadLineDate);
    boolean existsMentoringSession(String mentoringId, MentoringSessionOutDto mentoringSessionOutDto);
}
