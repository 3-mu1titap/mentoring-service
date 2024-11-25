package com.mentoring.demo.mentoring.adaptor.out.mysql.mapper;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringResponseOutDto;
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

    // 제거
    public static MentoringSessionEntity of(MentoringResponseOutDto mentoringResponseOutDto, MentoringSessionOutDto dto) {
        return MentoringSessionEntity.builder()
                        .uuid(dto.getUuid())
                        .mentoringEntity(
                                MentoringEntity.builder()
                                        .id(Long.valueOf(mentoringResponseOutDto.getId()))
                                        .mentoringUuid(mentoringResponseOutDto.getUuid())
                                        .name(mentoringResponseOutDto.getName())
                                        .description(mentoringResponseOutDto.getDescription())
                                        .detail(mentoringResponseOutDto.getDetail())
                                        .mentorUuid(mentoringResponseOutDto.getMentorUuid())
                                        .thumbnailUrl(mentoringResponseOutDto.getThumbnailUrl())
                                        .isReusable(mentoringResponseOutDto.getIsReusable())
                                        .isDeleted(mentoringResponseOutDto.getIsDeleted())
                                        .build()
                        )
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
                        .build();
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
    public static SessionAddAfterOutDto of (MentoringSessionEntity sessionEntity) {
        return SessionAddAfterOutDto.builder()
                    .sessionId(sessionEntity.getId().toString())
                    .sessionUuid(sessionEntity.getUuid())
                    .startDate(sessionEntity.getStartDate())
                    .endDate(sessionEntity.getEndDate())
                    .startTime(sessionEntity.getStartTime())
                    .endTime(sessionEntity.getEndTime())
                    .deadlineDate(sessionEntity.getDeadlineDate())
                    .minHeadCount(sessionEntity.getMinHeadCount())
                    .maxHeadCount(sessionEntity.getMaxHeadCount())
                    .price(sessionEntity.getPrice())
                    .isClosed(sessionEntity.getIsClosed())
                    .isDeleted(sessionEntity.getIsDeleted())
                    .createdAt(sessionEntity.getCreatedAt())
                    .updatedAt(sessionEntity.getUpdatedAt())
                    .build();

    }
}
