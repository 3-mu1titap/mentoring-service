package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringAddRequestOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.SessionValidationRequestOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeResponseOutDto;

import java.util.List;

public interface MentoringSessionRepositoryOutPort {
    List<MentoringSessionAddAfterOutDto> createMentoringSession(MentoringAddAfterOutDto mentoringAddAfterOutDto,
                                                                MentoringAddRequestOutDto mentoringAddRequestOutDto);
    SessionResponseOutDto getSessionResponseOutByUuid(String uuid);

    SessionTimeResponseOutDto getSessionTimeOutDtoByUuid(String uuid);

    void closeSession(String uuid);

    SessionTimeResponseOutDto validateSessionTime(SessionValidationRequestOutDto dto);
}
