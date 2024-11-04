package com.mentoring.demo.mentoring.adaptor.out.mysql.mapper;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringSessionAddAfterDto;
import com.mentoring.demo.mentoring.application.port.out.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class mentoringEntityMapper {
    /**
     * out port dto -> JPA entity 변환
     */

    // MentoringEditTransactionDto -> MentoringEntity
    public static MentoringEntity toMentoring( MentoringEditTransactionDto dto) {
        return MentoringEntity.builder()
                .id(Long.valueOf(dto.getId()))
                .mentoringUuid(dto.getUuid())
                .mentorUuid(dto.getMentorUuid())
                .name(dto.getName())
                .detail(dto.getDetail())
                .thumbnailUrl(dto.getThumbnailUrl())
                .isReusable(dto.getIsReusable())
                .isDeleted(dto.getIsDeleted())
                .build();
    }
    //



    public static MentoringResponseOutDto toMentoringResponseOutDto(MentoringEntity entity) {
        return MentoringResponseOutDto.builder()
                .id(entity.getId().toString())
                .uuid(entity.getMentoringUuid())
                .mentorUuid(entity.getMentorUuid())
                .name(entity.getName())
                .detail(entity.getDetail())
                .thumbnailUrl(entity.getThumbnailUrl())
                .isReusable(entity.getIsReusable())
                .isDeleted(entity.getIsDeleted())
                .build();
    }

    public static MentoringAddAfterOutDto toMentoringAddAfterOutDto(
            //MentoringEntity entity, List<MentoringSessionEntity> mentoringSessionEntities ) {
            MentoringEntity entity) {
        return MentoringAddAfterOutDto.builder()
                .mentoringId(entity.getId().toString())
                .mentoringUuid(entity.getMentoringUuid())
                .mentorUuid(entity.getMentorUuid())
                .name(entity.getName())
                .detail(entity.getDetail())
                .thumbnailUrl(entity.getThumbnailUrl())
                .isReusable(entity.getIsReusable())
                .isDeleted(entity.getIsDeleted())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
    public static List<MentoringSessionAddAfterDto> toMentoringSessionAddAfterDto(
                                                     List<MentoringSessionEntity> mentoringSessionEntities
    ) {
        return mentoringSessionEntities.stream()
                .map(session -> MentoringSessionAddAfterDto.builder()
                        .sessionId(session.getId().toString())
                        .sessionUuid(session.getUuid())
                        .mentoringId(session.getMentoringEntity().getId().toString())
                        .mentoringUuid(session.getMentoringEntity().getMentoringUuid())
                        .startDate(session.getStartDate())
                        .endDate(session.getEndDate())
                        .startTime(session.getStartTime())
                        .endTime(session.getEndTime())
                        .deadlineDatetime(session.getDeadlineDatetime())
                        .minHeadCount(session.getMinHeadCount())
                        .maxHeadCount(session.getMaxHeadCount())
                        .price(session.getPrice())
                        .isClosed(session.getIsClosed())
                        .isDeleted(session.getIsDeleted())
                        .createdAt(session.getCreatedAt())
                        .updatedAt(session.getUpdatedAt())
                        .build())
                .toList();

    }

 }
