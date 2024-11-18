package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.mapper.MentoringDtoMapper;
import com.mentoring.demo.mentoring.application.mapper.SessionDtoMapper;
import com.mentoring.demo.mentoring.application.port.in.MentoringSessionUseCase;
import com.mentoring.demo.mentoring.application.port.in.SendMessageUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.in.AddMentoringSessionDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.SessionValidationRequestDto;
import com.mentoring.demo.mentoring.application.port.out.MentoringSessionRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringSessionAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionCreatedAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeResponseOutDto;
import com.mentoring.demo.mentoring.application.port.in.dto.out.SessionValidationResponseDto;
import com.mentoring.demo.mentoring.domain.model.MentoringSessionDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class MentoringSessionService implements MentoringSessionUseCase {

    private final MentoringSessionRepositoryOutPort mentoringSessionRepositoryOutPort;
    private final SendMessageUseCase sendMessageUseCase;

    @Override
    public SessionResponseOutDto getSessionOutDtoByUuid(String uuid) {
        return mentoringSessionRepositoryOutPort.getSessionResponseOutByUuid(uuid);

    }

    @Override
    public SessionTimeResponseOutDto getSessionTimeOutDtoByUuid(String uuid) {
        return mentoringSessionRepositoryOutPort.getSessionTimeOutDtoByUuid(uuid);
    }

    @Override
    public void createSession(String mentoringUuid, List<AddMentoringSessionDto> addmentoringSessionDtoList) {
        List<MentoringSessionDomain> sessionDomains =
                MentoringSessionDomain.createSession(mentoringUuid, addmentoringSessionDtoList);
        List<MentoringSessionOutDto> sessionOutDtos = SessionDtoMapper.from(sessionDomains);

        SessionCreatedAfterOutDto session =
                mentoringSessionRepositoryOutPort.createSession(mentoringUuid, sessionOutDtos);
        // 세션 생성 후 이벤트 발생
        sendMessageUseCase.sendAddSessionMessage("add-session", session);
    }


    @Override
    public void closeSession(String uuid) {
        mentoringSessionRepositoryOutPort.closeSession(uuid);
    }

    @Override
    public void openSession(String uuid) {
        mentoringSessionRepositoryOutPort.openSession(uuid);
    }

    @Override
    public SessionValidationResponseDto validateSessionTime(SessionValidationRequestDto dto) {
        SessionTimeResponseOutDto sessionTimeResponse = mentoringSessionRepositoryOutPort.validateSessionTime(dto.toOutDto());
        return SessionValidationResponseDto.builder()
                    .isPossible(sessionTimeResponse == null)
                    .timeDuplicateResponse(sessionTimeResponse)
                    .build();
    }
}
