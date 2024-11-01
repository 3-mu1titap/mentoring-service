package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.application.mapper.MentoringDtoMapper;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddAfterDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.MentoringUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;
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

    @Override
    public void createMentoring(MentoringAddRequestDto mentoringAddRequestDto) {
        // 멘토링 도메인 생성
        MentoringDomain mentoring =
                MentoringDomain.createMentoring(mentoringAddRequestDto, UUID.randomUUID().toString());
        MentoringAddTransactionDto mentoringAddTransactionDto = MentoringDtoMapper.toMentoringTransactionDto(mentoring);
        MentoringAddAfterOutDto mentoringAddAfterOutDto =
                mentoringRepositoryOutPort.createMentoring(mentoringAddTransactionDto); // 멘토링 저장
        log.info("mentoringAddAfterOutDto : "+ mentoringAddAfterOutDto);

        // 멘토링 세션 도메인 생성
        List<MentoringSessionDomain> sessionDomain =
                MentoringSessionDomain.createMentoringSession(mentoringAddAfterOutDto, mentoringAddRequestDto.getSessionList());
        List<MentoringSessionTransactionDto> sessionTransactionDto =
                MentoringDtoMapper.toSessionTransactionDto(sessionDomain);
        mentoringAddTransactionDto.setSessionList(sessionTransactionDto);
        // 멘토링 세션 저장
        mentoringRepositoryOutPort.createMentoringSession(mentoringAddAfterOutDto, sessionTransactionDto);

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
