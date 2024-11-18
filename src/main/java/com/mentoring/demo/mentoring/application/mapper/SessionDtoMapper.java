package com.mentoring.demo.mentoring.application.mapper;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.domain.model.MentoringSessionDomain;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionDtoMapper {
    public static List<MentoringSessionOutDto> from(List<MentoringSessionDomain> sessionDomains) {
        return sessionDomains.stream()
                .map(session -> MentoringSessionOutDto.builder()
                        .uuid(session.getUuid())
                        .startDate(session.getStartDate())
                        .endDate(session.getEndDate())
                        .startTime(session.getStartTime())
                        .endTime(session.getEndTime())
                        .deadlineDate(session.getDeadlineDate())
                        .minHeadCount(session.getMinHeadCount())
                        .maxHeadCount(session.getMaxHeadCount())
                        .price(session.getPrice())
                        .isClosed(session.getIsClosed())
                        .isDeleted(session.getIsDeleted())
                        .build())
                .toList();
    }
}
