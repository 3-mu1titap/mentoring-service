package com.mentoring.demo.mentoring.adaptor.in.web;

import com.mentoring.demo.mentoring.adaptor.in.web.mapper.MentoringVoMapper;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.MentoringAddRequestVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.MentoringEditRequestVo;
import com.mentoring.demo.mentoring.application.port.in.MentoringCsvDataUseCase;
import com.mentoring.demo.mentoring.application.port.in.MentoringUseCase;
import com.mentoring.demo.mentoring.application.port.in.SendMessageUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringDataDto;
import com.mentoring.demo.mentoring.common.entity.BaseResponse;
import com.mentoring.demo.mentoring.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mentoring-service")
public class MentoringController {
    private final MentoringUseCase mentoringUseCase;
    private final MentoringCsvDataUseCase mentoringCsvDataUseCase;
    private final SendMessageUseCase sendMessageUseCase;

    @Operation(summary = "멘토링 생성", description = "멘토링 기본 정보와 세션정보 받아서 멘토링 생성", tags = {"멘토링"})
    @PostMapping("")
    public BaseResponse<Void> createMentoringAndSession(
            @RequestHeader("userUuid") String userUuid,
            @RequestBody MentoringAddRequestVo request
    ) {
        MentoringAddRequestDto createMentoringDto = MentoringVoMapper.of(userUuid, request);
        // 멘토링 생성
        mentoringUseCase.createMentoringWithSession(createMentoringDto);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "멘토링 수정", description = "멘토링 기본 정보 수정 (세션수정 X)", tags = {"멘토링"})
    @PutMapping("")
    public BaseResponse<Void> updateMentoring(@RequestBody MentoringEditRequestVo request) {

        mentoringUseCase.updateMentoring(MentoringVoMapper.from(request));

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // todo : 멘토링 삭제

    @Operation(summary = "멘토링 더미 데이터 저장", description = "멘토링 데이터 csv 파일로 데이터 저장", tags = {"멘토링 더미 데이터 저장 API"})
    @PostMapping(value = "/data", consumes = "multipart/form-data")
    public BaseResponse<Void> addData(@RequestParam("file") MultipartFile file) {

        // csv 파싱 하여 List 반환
        List<MentoringAddRequestDto> mentoringAddRequestDtoList = mentoringCsvDataUseCase.addData(file);

        // 파싱 된 dto 멘토링 등록
        for (MentoringAddRequestDto mentoringAddRequestDto : mentoringAddRequestDtoList) {
            mentoringUseCase.createMentoringWithSession(mentoringAddRequestDto);
        }


        // 카프카로 리뷰 서비스에 전송
        sendMessageUseCase.sendMentoringData("mentoring-data", MentoringDataDto.of(mentoringAddRequestDtoList));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
