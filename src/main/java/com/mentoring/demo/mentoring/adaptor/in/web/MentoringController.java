package com.mentoring.demo.mentoring.adaptor.in.web;

import com.mentoring.demo.mentoring.adaptor.out.kafka.KafkaProducer;
import com.mentoring.demo.mentoring.adaptor.in.web.mapper.MentoringVoMapper;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.MentoringAddRequestVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.MentoringEditRequestVo;
import com.mentoring.demo.mentoring.application.port.in.MentoringUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
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
public class MentoringController {
    private final MentoringUseCase mentoringUseCase;
    private final KafkaProducer kafkaProducer;

    @Operation(summary = "멘토링 생성" , description = "멘토링 기본 정보와 세션정보 받아서 멘토링 생성" ,tags = {"멘토링"})
    @PostMapping("")
    public BaseResponse<Void> createMentoringAndSession(@RequestBody MentoringAddRequestVo request) {
        MentoringAddRequestDto createMentoringDto = MentoringVoMapper.from(request);
        // 멘토링 생성
        mentoringUseCase.createMentoringWithSession(createMentoringDto);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "멘토링 수정", description = "멘토링 기본 정보 수정 (세션수정 X)" ,tags = {"멘토링"})
    @PutMapping("")
    public BaseResponse<Void> updateMentoring(@RequestBody MentoringEditRequestVo request) {

        mentoringUseCase.updateMentoring(MentoringVoMapper.from(request));

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // todo : 멘토링 삭제

}
