package com.mentoring.demo.mentoring.adaptor.in.web;

import com.mentoring.demo.mentoring.adaptor.in.kafka.KafkaProducer;
import com.mentoring.demo.mentoring.adaptor.in.web.mapper.MentoringVoMapper;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.MentoringAddRequestVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.MentoringEditRequestVo;
import com.mentoring.demo.mentoring.application.port.in.MentoringUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddAfterDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.common.entity.BaseResponse;
import com.mentoring.demo.mentoring.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mentoring")
public class MentoringController {
    private final MentoringUseCase mentoringUseCase;
    private final KafkaProducer kafkaProducer;

    @Operation(summary = "멘토링 생성" , description = "멘토링 기본 정보와 세션정보 받아서 멘토링 생성")
    @PostMapping("")
    public BaseResponse<Void> createMentoring(@RequestBody MentoringAddRequestVo request) {
        MentoringAddRequestDto createMentoringDto = MentoringVoMapper.toCreateMentoringDto(request);
        // 멘토링 생성
        MentoringAddAfterDto mentoringAddAfterDto = mentoringUseCase.createMentoring(createMentoringDto);
        // 멘토링 생성 이벤트 발생
        kafkaProducer.sendCreateMentoring("create-mentoring", mentoringAddAfterDto);


        //todo kafka send가 서비스 쪽으로 들어가면 된다

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "멘토링 수정", description = "멘토링 기본 정보 수정 (세션수정 X)")
    @PutMapping("")
    public BaseResponse<Void> updateMentoring(@RequestBody MentoringEditRequestVo request) {
        MentoringEditRequestDto mentoringEditRequestDto = MentoringVoMapper.toUpdateMentoringDto(request);
        mentoringUseCase.updateMentoring(mentoringEditRequestDto);

        kafkaProducer.sendUpdateMentoring("update-mentoring", mentoringEditRequestDto);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // todo : 멘토링 삭제

}
