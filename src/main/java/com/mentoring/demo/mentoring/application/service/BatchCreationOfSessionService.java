package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.mapper.SessionDtoMapper;
import com.mentoring.demo.mentoring.application.port.in.BatchCreationOfSessionUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.in.BatchCreationOfSessionDto;
import com.mentoring.demo.mentoring.application.port.out.MentoringInquiryOutPort;
import com.mentoring.demo.mentoring.application.port.out.MentoringSessionRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.SendMessageOutPort;
import com.mentoring.demo.mentoring.application.port.out.SessionInquiryRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionCreatedAfterOutDto;
import com.mentoring.demo.mentoring.domain.model.MentoringSessionDomain;
import com.mentoring.demo.mentoring.domain.vo.SessionBatchCreationManagementVo;
import com.mentoring.demo.mentoring.domain.vo.TimeRange;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class BatchCreationOfSessionService implements BatchCreationOfSessionUseCase {
    private final MentoringInquiryOutPort mentoringInquiryOutPort;
    private final SessionInquiryRepositoryOutPort sessionInquiryRepositoryOutPort;
    private final MentoringSessionRepositoryOutPort mentoringSessionRepositoryOutPort;
    private final SendMessageOutPort sendMessageOutPort;

    @Override
    public Integer batchCreationOfSession(BatchCreationOfSessionDto dto) {
        /**
         * 비즈니스 정책
         * 1. '기존 세션과 겹치면 생성 안됨'
         * 2. '생성마감일까지만 생성. 생성마감일은 오늘 날짜로부터 최대 90일 뒤 까지 생성'
         * 3. '요일 중복, 요일에 시간리스트 중복 되면 안됨
         */
        // 일괄생성 마감일 유효성 검사
        SessionBatchCreationManagementVo.validateDeadlineDate(dto.getDeadLineDate());
        // todo : 요일 중복, 요일에 시간리스트 중복 체크 => 문제시 에러 발생
        // 날짜 : [ {13:00~14:00} , {14:00~15:00}... ] 형태로 변환(생성마감일 까지, List 형태임)
        Map<LocalDate, List<TimeRange>> SessionListByDateInputMap =
                SessionBatchCreationManagementVo.convertSessionListByDateUntilDeadline(dto.getTimeSlotDtoList(), dto.getDeadLineDate());

        /**
         * insert 시 비교
         */
        // 멘토링 정보 조회
        MentoringResponseOutDto mentoringResponseOutDto =
                mentoringInquiryOutPort.getMentoringResponseByMentoringUuid(dto.getMentoringUuid());

        List<MentoringSessionDomain> sessionDomains =
                MentoringSessionDomain.createBatchSession(dto.getMentoringUuid(), SessionListByDateInputMap);
        List<MentoringSessionOutDto> sessionOutDtos = SessionDtoMapper.from(sessionDomains);

        SessionCreatedAfterOutDto sessionCreatedAfterOutDto = SessionCreatedAfterOutDto.builder()
                .mentoringUuid(dto.getMentoringUuid())
                .mentoringId(mentoringResponseOutDto.getId())
                .mentoringName(mentoringResponseOutDto.getName())
                .mentorUuid(mentoringResponseOutDto.getMentorUuid())
                .sessionAddAfterOutDtos(new ArrayList<>())
                .build();
        // db에 저장된 세션과 겹치지 않으면 생성
        sessionOutDtos.forEach(mentoringSessionOutDto -> {
            if(!sessionInquiryRepositoryOutPort.existsMentoringSession(mentoringResponseOutDto.getId(),mentoringSessionOutDto)){
                sessionCreatedAfterOutDto.getSessionAddAfterOutDtos()
                        .add(mentoringSessionRepositoryOutPort.createSession(mentoringResponseOutDto,mentoringSessionOutDto));
            }
        });

        if(!sessionCreatedAfterOutDto.getSessionAddAfterOutDtos().isEmpty()){
            // 세션 생성 후 이벤트 발생
            sendMessageOutPort.sendAddSessionMessage("add-session", sessionCreatedAfterOutDto);
        }
        return sessionCreatedAfterOutDto.getSessionAddAfterOutDtos().size();
        /**
         * 애플리케이션 비교
         */
        //        Map<LocalDate, List<TimeRange>> sessionTimeUntilDeadline =
        //                mentoringSessionRepositoryOutPort.getSessionTimeUntilDeadline(mentoringId, dto.getDeadLineDate());
        //        log.info("===================DB=====================");
        //        for (LocalDate date : sessionTimeUntilDeadline.keySet()) {
        //            log.info(date + " (" + date.getDayOfWeek() + ")");
        //            for (TimeRange timeRange : sessionTimeUntilDeadline.get(date)) {
        //                log.info(timeRange.getStartTime() + " ~ " + timeRange.getEndTime()+ " => " +timeRange.getIsNextDay());
        //            }
        //        }
        //입력값 <-> 기존 세션 비교
        // 결과적으로 저장할 세션 리스트를 담을 변수
//        Map<LocalDate, List<TimeRange>> maps =
//                SessionBatchCreationManagementVo.filterDuplicateSessions(SessionListByDateMaps, sessionTimeUntilDeadline);
    }

}
