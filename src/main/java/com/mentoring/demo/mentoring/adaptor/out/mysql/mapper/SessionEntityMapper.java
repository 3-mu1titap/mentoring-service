package com.mentoring.demo.mentoring.adaptor.out.mysql.mapper;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringSessionAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionCreatedAfterOutDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionEntityMapper {
    public static List<MentoringSessionEntity> of(MentoringEntity mentoringEntity, List<MentoringSessionOutDto> sessionOutDtos) {
        return sessionOutDtos.stream()
                .map(dto -> MentoringSessionEntity.builder()
                        .uuid(dto.getUuid())
                        .mentoringEntity(mentoringEntity)
                        .startDate(dto.getStartDate())
                        .endDate(dto.getEndDate())
                        .startTime(dto.getStartTime())
                        .endTime(dto.getEndTime())
                        .deadlineDate(dto.getDeadlineDate())
                        .minHeadCount(dto.getMinHeadCount())
                        .maxHeadCount(dto.getMaxHeadCount())
                        .price(dto.getPrice())
                        .isClosed(dto.getIsClosed())
                        .isDeleted(dto.getIsDeleted())
                        .build())
                .collect(Collectors.toList());
    }

    public static SessionCreatedAfterOutDto of (List<MentoringSessionEntity> sessionEntities, MentoringEntity mentoringEntity) {
        return SessionCreatedAfterOutDto.builder()
                .mentoringId(mentoringEntity.getId().toString())
                .mentoringUuid(mentoringEntity.getMentoringUuid())
                .mentorUuid(mentoringEntity.getMentorUuid())
                .mentoringName(mentoringEntity.getName())
                .sessionAddAfterOutDtos(
                        sessionEntities.stream()
                                .map(session -> SessionAddAfterOutDto.builder()
                                        .sessionId(session.getId().toString())
                                        .sessionUuid(session.getUuid())
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
                                        .createdAt(session.getCreatedAt())
                                        .updatedAt(session.getUpdatedAt())
                                        .build())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
