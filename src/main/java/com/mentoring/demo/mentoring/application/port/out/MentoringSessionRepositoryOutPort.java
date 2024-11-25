package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.in.*;
import com.mentoring.demo.mentoring.application.port.out.dto.out.*;
import com.mentoring.demo.mentoring.domain.vo.TimeRange;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MentoringSessionRepositoryOutPort {
    List<MentoringSessionAddAfterOutDto> createMentoringSession(MentoringAddAfterOutDto mentoringAddAfterOutDto,
                                                                MentoringAddRequestOutDto mentoringAddRequestOutDto);

    void closeSession(String uuid);

    void openSession(String uuid);

    SessionCreatedAfterOutDto createSessions(String mentoringUuid, List<MentoringSessionOutDto> sessionOutDtos);
    SessionAddAfterOutDto createSession(MentoringResponseOutDto mentoringResponseOutDto, MentoringSessionOutDto sessionOutDto);

    SessionTimeResponseOutDto validateSessionTime(SessionValidationRequestOutDto dto);


}
