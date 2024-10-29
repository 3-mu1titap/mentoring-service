package com.mentoring.demo.mentoring.application.mapper;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringSessionTransactionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringTransactionDto;
import com.mentoring.demo.mentoring.domain.model.MentoringDomain;
import com.mentoring.demo.mentoring.domain.model.MentoringSessionDomain;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MentoringDtoMapper {

    public static MentoringTransactionDto toMentoringTransactionDto(MentoringDomain domain) {
        return MentoringTransactionDto.builder()
                .mentoringUuid(domain.getUuid())
                .name(domain.getName())
                .detail(domain.getDetail())
                .mentorUuid(domain.getMentorUuid())
                .thumbnailUrl(domain.getThumbnailUrl())
                .isReusable(domain.getIsReusable())
                //.sessionList()
                .build();
    }

    public static List<MentoringSessionTransactionDto> toSessionTransactionDto(
                                                                    MentoringDomain domain,
                                                                    List<MentoringSessionDomain> sessionDomain) {
        // MentoringSessionDomain -> MentoringSessionTransactionDto 변환
        return sessionDomain.stream()
                .map(session -> MentoringSessionTransactionDto.builder()
                        .uuid(session.getUuid())
                        //.mentoringId()
                        .startDate(session.getStartDate())
                        .endDate(session.getEndDate())
                        .startTime(session.getStartTime())
                        .endTime(session.getEndTime())
                        .deadlineDatetime(session.getDeadlineDate())
                        .minHeadCount(session.getMinHeadCount())
                        .maxHeadCount(session.getMaxHeadCount())
                        .price(session.getPrice())
                        .isClosed(session.getIsClosed())
                        .build())
                .toList();


    }

}
