package com.mentoring.demo.mentoring.adaptor.out.mysql.mapper;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringEditTransactionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringSessionAddAfterOutDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class mentoringEntityMapper {
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
            MentoringEntity entity, List<MentoringSessionEntity> mentoringSessionEntities ) {
        return MentoringAddAfterOutDto.builder()
                .mentoringId(entity.getId().toString())
                .mentoringUuid(entity.getMentoringUuid())
                .mentorUuid(entity.getMentorUuid())
                .name(entity.getName())
                .detail(entity.getDetail())
                .thumbnailUrl(entity.getThumbnailUrl())
                .isReusable(entity.getIsReusable())
                .isDeleted(entity.getIsDeleted())
                .mentoringSessionAddAfterOutDtoList(
                        mentoringSessionEntities
                                .stream()
                                .map(mentoringSessionEntity -> MentoringSessionAddAfterOutDto.builder()
                                        .sessionId(mentoringSessionEntity.getId().toString())
                                        .sessionUuid(mentoringSessionEntity.getUuid())
                                        .mentoringId(mentoringSessionEntity.getMentoringEntity().getId().toString())
                                        .startDate(mentoringSessionEntity.getStartDate())
                                        .endDate(mentoringSessionEntity.getEndDate())
                                        .startTime(mentoringSessionEntity.getStartTime())
                                        .endTime(mentoringSessionEntity.getEndTime())
                                        .deadlineDatetime(mentoringSessionEntity.getDeadlineDatetime())
                                        .minHeadCount(mentoringSessionEntity.getMinHeadCount())
                                        .maxHeadCount(mentoringSessionEntity.getMaxHeadCount())
                                        .price(mentoringSessionEntity.getPrice())
                                        .isClosed(mentoringSessionEntity.getIsClosed())
                                        .build())
                                .toList()
                )
                .build();
    }
}
