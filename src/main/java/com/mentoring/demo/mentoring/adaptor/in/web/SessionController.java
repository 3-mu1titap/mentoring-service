package com.mentoring.demo.mentoring.adaptor.in.web;

import com.mentoring.demo.mentoring.adaptor.in.web.mapper.SessionVoMapper;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.BatchCreationOfSessionVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.SessionAddRequestVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.SessionValidationRequestVo;
import com.mentoring.demo.mentoring.application.port.in.BatchCreationOfSessionUseCase;
import com.mentoring.demo.mentoring.application.port.in.MentoringSessionUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.out.SessionValidationResponseDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeResponseOutDto;
import com.mentoring.demo.mentoring.common.entity.BaseResponse;
import com.mentoring.demo.mentoring.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mentoring-service")
public class SessionController {
    private final MentoringSessionUseCase mentoringSessionUseCase;
    private final BatchCreationOfSessionUseCase batchCreationOfSessionUseCase;
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
    @PutMapping("/session-close/{uuid}")
    public void updateSessionClosedTrue(@PathVariable(name = "uuid") String uuid) {
        mentoringSessionUseCase.closeSession(uuid);
        log.info("updateSessionClosed : {}", uuid);
    }

    @Operation(summary = "세션 열림 상태로 업데이트 (session-request-service 의 feign client 용 api)" ,
            description = "세션 uuid 로 세션 열림 상태로 업데이트" ,tags = {"feign client"})
    @PutMapping("/session-open/{uuid}")
    public void updateSessionClosedFalse(@PathVariable(name = "uuid") String uuid) {
        mentoringSessionUseCase.openSession(uuid);
        log.info("updateSessionClosed : {}", uuid);
    }

    @Operation(summary = "멘토링 세션 시간 유효한지 검증" , description = "시작/종료 날짜 시간, 멘토 uuid 로 유효한지 검증"
            ,tags = {"멘토링 세션"} )
    @PostMapping("/validate-session-time")
    public BaseResponse<SessionValidationResponseDto> validateSessionTime(
            @RequestParam(name ="mentoringUuid", required = true) String mentoringUuid,
            SessionValidationRequestVo requestVo
    ) {
        return new BaseResponse<>(mentoringSessionUseCase.validateSessionTime(SessionVoMapper.of(mentoringUuid,requestVo)));
    }

    @Operation(summary = "멘토링 세션 추가" , description = "멘토링 세션 추가"
            ,tags = {"멘토링 세션"} )
    @PostMapping("/session")
    public BaseResponse<Void> createSession(
            @RequestParam(name ="mentoringUuid", required = true) String mentoringUuid,
            @RequestBody List<SessionAddRequestVo> sessionVoList
    ) {
        mentoringSessionUseCase.createSession(mentoringUuid,SessionVoMapper.from(sessionVoList));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "멘토링 세션 일괄생성" , description = "멘토링 세션 일괄생성 <br/>"+
            "요일 리스트(dayOfWeekList) 입력 안들어오면 매일 생성. <br/>"+
            "기존 세션과 겹치면 생성 안된다"
            ,tags = {"멘토링 세션"} )
    @PostMapping("/batch-session")
    public BaseResponse<Integer> batchCreationOfSession(@RequestBody BatchCreationOfSessionVo request)
    {

        return new BaseResponse<>(batchCreationOfSessionUseCase.batchCreationOfSession(SessionVoMapper.from(request)));
    }
}
