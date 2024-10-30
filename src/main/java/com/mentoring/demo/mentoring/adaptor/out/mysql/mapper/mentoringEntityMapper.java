package com.mentoring.demo.mentoring.adaptor.out.mysql.mapper;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringEditTransactionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringResponseOutDto;
import org.springframework.stereotype.Component;

@Component
public class mentoringEntityMapper {

    public static MentoringEntity toMentoring(
            MentoringEntity entity, MentoringEditTransactionDto dto
    ) {
        return MentoringEntity.builder()
                .id(entity.getId())
                .mentoringUuid(entity.getMentoringUuid())
                .name(dto.getName())
                .detail(dto.getDetail())
                .mentorUuid(entity.getMentorUuid())
                .thumbnailUrl(dto.getThumbnailUrl())
                .isReusable(dto.getIsReusable())
                .isDeleted(entity.getIsDeleted())
                .build();
    }
}
