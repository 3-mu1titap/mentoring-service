package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.mapper.MentoringDtoMapper;
import com.mentoring.demo.mentoring.application.port.in.SendMessageUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.MentoringUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.application.port.out.MentoringRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.*;
import com.mentoring.demo.mentoring.domain.model.MentoringDomain;
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
        // 멘토링 세션+카테고리 null 검사
        mentoring.checkSessionAndCategory();
        // 멘토링 시작날짜 검사 (오늘 날짜에 시작하는 세션이 있는지 검증)
        mentoring.checkForTodaySessions();

        MentoringAddRequestOutDto mentoringAddRequestOutDto = MentoringDtoMapper.toMentoringTransactionDto(mentoring);
        // 멘토링 저장
        MentoringAddAfterOutDto mentoringAddAfterOutDto =
                mentoringRepositoryOutPort.createMentoring(mentoringAddRequestOutDto);
        // 멘토링 세션 저장
        List<MentoringSessionAddAfterOutDto> mentoringSessionAddAfterDto =
                mentoringRepositoryOutPort.createMentoringSession(mentoringAddAfterOutDto, mentoringAddRequestOutDto);
        // 멘토링 카테고리 저장
        List<MentoringCategoryAfterOutDto> mentoringCategoryAfterOutDto =
                mentoringRepositoryOutPort.createMentoringCategory(mentoringAddAfterOutDto, mentoringAddRequestOutDto);

        // 저장 이후 시점의 세션+카테고리를 mentoringAddAfterOutDto에 set
        mentoringAddAfterOutDto.setMentoringSessionAddAfterOutDtoList(mentoringSessionAddAfterDto);
        mentoringAddAfterOutDto.setMentoringCategoryAfterOutDtoList(mentoringCategoryAfterOutDto);

        sendMessageUseCase.sendCreateMentoringMessage("create-mentoring", mentoringAddAfterOutDto);
    }

    @Override
    public void updateMentoring(MentoringEditRequestDto mentoringEditRequestDto) {
        MentoringResponseOutDto mentoringResponseOutDto =
                mentoringRepositoryOutPort.findByMentoringUuid(mentoringEditRequestDto.getUuid());
        // 업데이트 검사
        MentoringDomain.checkUpdateObject(mentoringResponseOutDto);
        // 멘토링 카테고리 삭제
        if (mentoringEditRequestDto.getCategoryList() != null) {
            mentoringRepositoryOutPort.deleteMentoringCategory(mentoringEditRequestDto.getUuid());
        }
        MentoringDomain mentoringDomain =
                MentoringDomain.updateMentoring(mentoringEditRequestDto, mentoringResponseOutDto);

        MentoringEditRequestOutDto mentoringEditRequestOutDto =
                MentoringDtoMapper.toMentoringEditRequestOutDto(mentoringDomain);
        // 멘토링+카테고리 업데이트
        List<MentoringCategoryAfterOutDto> mentoringCategoryAfterOutDtos =
                mentoringRepositoryOutPort.updateMentoring(mentoringEditRequestOutDto);
        mentoringEditRequestOutDto.setCategoryList(mentoringCategoryAfterOutDtos);
        sendMessageUseCase.sendUpdateMentoringMessage("update-mentoring", mentoringEditRequestOutDto);
    }
}
