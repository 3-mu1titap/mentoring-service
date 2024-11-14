package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.port.in.MentoringSessionUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.in.SessionValidationRequestDto;
import com.mentoring.demo.mentoring.application.port.out.MentoringSessionRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeResponseOutDto;
import com.mentoring.demo.mentoring.application.port.in.dto.out.SessionValidationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class MentoringSessionService implements MentoringSessionUseCase {

    private final MentoringSessionRepositoryOutPort mentoringSessionRepositoryOutPort;

    @Override
    public SessionResponseOutDto getSessionOutDtoByUuid(String uuid) {
        return mentoringSessionRepositoryOutPort.getSessionResponseOutByUuid(uuid);

    }

    @Override
    public SessionTimeResponseOutDto getSessionTimeOutDtoByUuid(String uuid) {
        return mentoringSessionRepositoryOutPort.getSessionTimeOutDtoByUuid(uuid);
    }

    @Override
    public void closeSession(String uuid) {
        mentoringSessionRepositoryOutPort.closeSession(uuid);
    }

    @Override
    public SessionValidationResponseDto validateSessionTime(SessionValidationRequestDto dto) {
        log.info("서비스1");
        SessionTimeResponseOutDto sessionTimeResponse = mentoringSessionRepositoryOutPort.validateSessionTime(dto.toOutDto());
        log.info("서비스2");
        return SessionValidationResponseDto.builder()
                    .isPossible(sessionTimeResponse == null)
                    .timeDuplicateResponse(sessionTimeResponse)
                    .build();
    }
}
