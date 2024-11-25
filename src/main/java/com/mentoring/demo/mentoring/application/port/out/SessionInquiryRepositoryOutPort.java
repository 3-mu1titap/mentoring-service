package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.DeadlinePastSessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeResponseOutDto;
import com.mentoring.demo.mentoring.domain.vo.TimeRange;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface SessionInquiryRepositoryOutPort {

    boolean existsMentoringSession(String mentoringId, MentoringSessionOutDto mentoringSessionOutDto);

    SessionResponseOutDto getSessionResponseOutByUuid(String uuid);

    SessionTimeResponseOutDto getSessionTimeOutDtoByUuid(String uuid);

    List<DeadlinePastSessionResponseOutDto> getDeadLinePastSessionList(LocalDate now);

    Map<LocalDate, List<TimeRange>> getSessionTimeUntilDeadline(String mentoringId , LocalDate deadLineDate);
}
