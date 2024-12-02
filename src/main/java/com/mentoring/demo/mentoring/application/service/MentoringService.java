package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.mapper.MentoringDtoMapper;
import com.mentoring.demo.mentoring.application.port.in.SendMessageUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.MentoringUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.application.port.out.MentoringHashtagRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.MentoringRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.MentoringSessionRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.SendMessageOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.in.*;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringCategoryAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringHashTagAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringSessionAddAfterOutDto;
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
    private final MentoringHashtagRepositoryOutPort mentoringHashtagRepositoryOutPort;
    private final MentoringSessionRepositoryOutPort mentoringSessionRepositoryOutPort;
    private final SendMessageOutPort sendMessageOutPort;
    
    @Override
    public void createMentoringWithSession(MentoringAddRequestDto mentoringAddRequestDto) {
        // 멘토링 도메인 생성
        MentoringDomain mentoring =
                MentoringDomain.createMentoring(mentoringAddRequestDto, UUID.randomUUID().toString());
        // 멘토링 시작날짜 검사 (오늘 날짜에 시작하는 세션이 있는지 검증)
        mentoring.checkForTodayStartSessions();

        MentoringAddRequestOutDto mentoringAddRequestOutDto = MentoringDtoMapper.from(mentoring);
        // 멘토링 저장
        MentoringAddAfterOutDto mentoringAddAfterOutDto =
                mentoringRepositoryOutPort.createMentoring(mentoringAddRequestOutDto);
        // 멘토링 세션 저장
        if(mentoringAddRequestOutDto.getSessionList() != null){
            List<MentoringSessionAddAfterOutDto> mentoringSessionAddAfterDto =
                    mentoringSessionRepositoryOutPort.createMentoringSession(mentoringAddAfterOutDto, mentoringAddRequestOutDto);
            mentoringAddAfterOutDto.setMentoringSessionAddAfterOutDtoList(mentoringSessionAddAfterDto);
        }
        // 멘토링 카테고리 저장
        if(mentoringAddRequestOutDto.getCategoryList() != null){
            List<MentoringCategoryAfterOutDto> mentoringCategoryAfterOutDto =
                    mentoringRepositoryOutPort.createMentoringCategory(mentoringAddAfterOutDto, mentoringAddRequestOutDto);
            mentoringAddAfterOutDto.setMentoringCategoryAfterOutDtoList(mentoringCategoryAfterOutDto);
        }
        // 멘토링 해시태그 저장
        if(mentoringAddRequestOutDto.getHashtagList() != null){
            MentoringHashTagAfterOutDto mentoringHashtag =
                    mentoringHashtagRepositoryOutPort.createMentoringHashtag(mentoringAddAfterOutDto, mentoringAddRequestOutDto.getHashtagList());
            mentoringAddAfterOutDto.setMentoringHashTagAfterOutDto(mentoringHashtag);
        }

        sendMessageOutPort.sendCreateMentoringMessage("create-mentoring", mentoringAddAfterOutDto);

    }

    @Override
    public void updateMentoring(MentoringEditRequestDto mentoringEditRequestDto) {
        MentoringResponseOutDto mentoringResponseOutDto =
                mentoringRepositoryOutPort.findByMentoringUuid(mentoringEditRequestDto.getUuid());
        // 업데이트 검사
        MentoringDomain.checkUpdateObject(mentoringResponseOutDto);
        // 멘토링 카테고리 삭제
        if (mentoringEditRequestDto.getCategoryList() != null) {
            mentoringRepositoryOutPort.deleteMentoringCategory(mentoringResponseOutDto.getId());
        }
        // 멘토링 해시태그 삭제
        if (mentoringEditRequestDto.getHashTagList() != null) {
            mentoringHashtagRepositoryOutPort.deleteMentoringHashtag(mentoringResponseOutDto.getId());
        }
        MentoringDomain mentoringDomain =
                MentoringDomain.updateMentoring(mentoringEditRequestDto, mentoringResponseOutDto);

        MentoringEditRequestOutDto mentoringEditRequestOutDto = MentoringEditRequestOutDto.from(mentoringDomain);
        // 멘토링+카테고리+해시태그 업데이트
        mentoringEditRequestOutDto.setCategoryList(mentoringRepositoryOutPort.updateMentoring(mentoringEditRequestOutDto));
        // 멘토링 업데이트 메시지 발행
        sendMessageOutPort.sendUpdateMentoringMessage("update-mentoring", mentoringEditRequestOutDto);
    }
}

