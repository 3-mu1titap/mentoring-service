package com.mentoring.demo.mentoring.adaptor.in.web;

import com.mentoring.demo.mentoring.adaptor.in.web.mapper.SessionVoMapper;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.SessionValidationRequestVo;
import com.mentoring.demo.mentoring.application.port.in.MentoringSessionUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.in.SessionValidationRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.out.SessionValidationResponseDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeResponseOutDto;
import com.mentoring.demo.mentoring.common.entity.BaseResponse;
import com.mentoring.demo.mentoring.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mentoring-service")
public class SessionController {
    private final MentoringSessionUseCase mentoringSessionUseCase;

    @Operation(summary = "세션 정보 조회[최대정원수, 예약마감일] (session-request-service 의 feign client 용 api)"
            ,tags = {"feign client"} )
    @GetMapping("/session/{uuid}")
    public SessionResponseOutDto getSessionOutDtoByUuid(@PathVariable(name = "uuid") String uuid) {
        return mentoringSessionUseCase.getSessionOutDtoByUuid(uuid);
    }
    @Operation(summary = "세션 정보 조회[시작 날짜,시간과 종료 날짜,시간] (session-request-query-service 의 feign client 용 api)"
            ,tags = {"feign client"} )
    @GetMapping("/session-time/{uuid}")
    public SessionTimeResponseOutDto getSessionTimeOutDtoByUuid(@PathVariable(name = "uuid") String uuid) {
        SessionTimeResponseOutDto sessionTimeOutDtoByUuid = mentoringSessionUseCase.getSessionTimeOutDtoByUuid(uuid);
        log.info("sessionTimeOutDtoByUuid : {}", sessionTimeOutDtoByUuid);
        return sessionTimeOutDtoByUuid;
    }
    @Operation(summary = "세션 닫힘 상태로 업데이트 (session-request-service 의 feign client 용 api)" ,
                description = "세션 uuid 로 세션 닫힘 상태로 업데이트" ,tags = {"feign client"})
    @PutMapping("/session/{uuid}")
    public void updateSessionClosed(@PathVariable(name = "uuid") String uuid) {
        mentoringSessionUseCase.closeSession(uuid);
    }

    @Operation(summary = "멘토링 세션 시간 유효한지 검증" , description = "시작/종료 날짜 시간, 멘토 uuid 로 유효한지 검증"
            ,tags = {"멘토링 세션"} )
    @GetMapping("/validate-session-time")
    public BaseResponse<SessionValidationResponseDto> validateSessionTime(SessionValidationRequestVo requestVo) {
        return new BaseResponse<>(mentoringSessionUseCase.validateSessionTime(SessionVoMapper.from(requestVo)));
    }
}
