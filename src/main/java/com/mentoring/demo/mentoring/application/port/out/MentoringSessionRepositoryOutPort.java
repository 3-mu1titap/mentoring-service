package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.in.*;
import com.mentoring.demo.mentoring.application.port.out.dto.out.*;

import java.time.LocalDate;
import java.util.List;

public interface MentoringSessionRepositoryOutPort {
    List<MentoringSessionAddAfterOutDto> createMentoringSession(MentoringAddAfterOutDto mentoringAddAfterOutDto,
                                                                MentoringAddRequestOutDto mentoringAddRequestOutDto);
    SessionResponseOutDto getSessionResponseOutByUuid(String uuid);

    SessionTimeResponseOutDto getSessionTimeOutDtoByUuid(String uuid);

    void closeSession(String uuid);

    void openSession(String uuid);

    SessionCreatedAfterOutDto createSession(String mentoringUuid, List<MentoringSessionOutDto> sessionOutDtos);

    SessionTimeResponseOutDto validateSessionTime(SessionValidationRequestOutDto dto);

    List<DeadlinePastSessionResponseOutDto> getPastDeadlineSessions(LocalDate now);
}
