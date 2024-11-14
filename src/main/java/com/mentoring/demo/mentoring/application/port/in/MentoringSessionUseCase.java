package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.application.port.in.dto.in.SessionValidationRequestDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeResponseOutDto;
import com.mentoring.demo.mentoring.application.port.in.dto.out.SessionValidationResponseDto;

public interface MentoringSessionUseCase {
    SessionResponseOutDto getSessionOutDtoByUuid(String uuid);

    SessionTimeResponseOutDto getSessionTimeOutDtoByUuid(String uuid);

    // 세션 닫기 (최대정원 다 찰 경우)
    void closeSession(String uuid);

    // 멘토링 세션 유효 시간 검증
    SessionValidationResponseDto validateSessionTime(SessionValidationRequestDto dto);

}
