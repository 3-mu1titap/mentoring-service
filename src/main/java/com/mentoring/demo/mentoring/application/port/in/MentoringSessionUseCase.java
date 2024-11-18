package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.application.port.in.dto.in.AddMentoringSessionDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.SessionValidationRequestDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringSessionAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeResponseOutDto;
import com.mentoring.demo.mentoring.application.port.in.dto.out.SessionValidationResponseDto;

import java.util.List;

public interface MentoringSessionUseCase {
    SessionResponseOutDto getSessionOutDtoByUuid(String uuid);

    SessionTimeResponseOutDto getSessionTimeOutDtoByUuid(String uuid);
    void createSession(String mentoringUuid, List<AddMentoringSessionDto> addmentoringSessionDtoList);

    // 세션 닫기 (최대정원 다 찰 경우)
    void closeSession(String uuid);
    // 세션 열기 (최대 정원에서 취소로 빈 자리 생길 경우)
    void openSession(String uuid);

    // 멘토링 세션 유효 시간 검증
    SessionValidationResponseDto validateSessionTime(SessionValidationRequestDto dto);

}
