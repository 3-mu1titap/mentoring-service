package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.application.mapper.MentoringDtoMapper;
import com.mentoring.demo.mentoring.application.port.in.SendMessageUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddAfterDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.MentoringUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringSessionAddAfterDto;
import com.mentoring.demo.mentoring.application.port.out.MentoringRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.*;
import com.mentoring.demo.mentoring.domain.model.MentoringDomain;
import com.mentoring.demo.mentoring.domain.model.MentoringSessionDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class MentoringService implements MentoringUseCase {
    private final MentoringRepositoryOutPort mentoringRepositoryOutPort;
    private final SendMessageUseCase sendMessageUseCase;

    @Override
    public void createMentoringWithSession(MentoringAddRequestDto mentoringAddRequestDto) {
        // 멘토링 도메인 생성
        MentoringDomain mentoring =
                MentoringDomain.createMentoring(mentoringAddRequestDto, UUID.randomUUID().toString());
        MentoringAddRequestOutDto mentoringAddRequestOutDto = MentoringDtoMapper.toMentoringTransactionDto(mentoring);
        MentoringAddAfterOutDto mentoringAddAfterOutDto =
                mentoringRepositoryOutPort.createMentoring(mentoringAddRequestOutDto); // 멘토링 저장

        // 멘토링 세션 도메인 생성
        List<MentoringSessionDomain> sessionDomain =
                MentoringSessionDomain.createMentoringSession(mentoringAddAfterOutDto, mentoringAddRequestDto.getSessionList());
        List<MentoringSessionOutDto> sessionOutDto = MentoringDtoMapper.toSessionOutDto(sessionDomain);
        mentoringAddRequestOutDto.setSessionList(sessionOutDto);
        // 멘토링 세션 저장 (이미 저장된 멘토링을 인자로 넣음)
        List<MentoringSessionAddAfterDto> mentoringSessionAddAfterDtos =
                mentoringRepositoryOutPort.createMentoringSession(mentoringAddAfterOutDto, sessionOutDto);


        mentoringAddAfterOutDto.setMentoringSessionAddAfterOutDtoList(mentoringSessionAddAfterDtos);
        log.info("mentoringAddAfterOutDto: {}", mentoringAddAfterOutDto);
        // 메시지 전송
        sendMessageUseCase.sendCreateMentoringMessage("create-mentoring", mentoringAddAfterOutDto);


    }

    @Override
    public void updateMentoring(MentoringEditRequestDto mentoringEditRequestDto) {
        MentoringResponseOutDto mentoringResponseOutDto =
                mentoringRepositoryOutPort.findByMentoringUuid(mentoringEditRequestDto.getUuid());
        log.info("mentoringResponseOutDto: {}", mentoringResponseOutDto);

        MentoringDomain mentoringDomain =
                MentoringDomain.updateMentoring(mentoringEditRequestDto, mentoringResponseOutDto);
        MentoringEditTransactionDto mentoringEditTransactionDto =
                MentoringDtoMapper.toMentoringEditTransactionDto(mentoringDomain);
        mentoringRepositoryOutPort.updateMentoring(mentoringEditTransactionDto);

    }
}
