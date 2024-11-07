package com.mentoring.demo.mentoring.application.port.out.dto;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentoringSessionOutDto {

    private String uuid;

    private String mentoringId;
    private String mentoringUuid;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate deadlineDate;

    private Integer minHeadCount;

    private Integer maxHeadCount;

    private Integer price;

    private Boolean isClosed;
    private Boolean isDeleted;

    public static List<MentoringSessionEntity> toEntities(
            MentoringAddAfterOutDto afterOutDto, MentoringAddRequestOutDto outDto)
    {
        MentoringEntity mentoringEntity = MentoringEntity.builder()
                .id(Long.valueOf(afterOutDto.getMentoringId()))
                .mentoringUuid(outDto.getMentoringUuid())
                .name(outDto.getName())
                .detail(outDto.getDetail())
                .mentorUuid(outDto.getMentorUuid())
                .thumbnailUrl(outDto.getThumbnailUrl())
                .isReusable(outDto.getIsReusable())
                .isDeleted(outDto.getIsDeleted())
                .build();

        return outDto.getSessionList().stream()
                .map(
                    mentoringSessionTransactionDto
                            -> MentoringSessionEntity.builder()
                                .uuid(mentoringSessionTransactionDto.getUuid())
                                .mentoringEntity(mentoringEntity)
                                .startDate(mentoringSessionTransactionDto.getStartDate())
                                .endDate(mentoringSessionTransactionDto.getEndDate())
                                .startTime(mentoringSessionTransactionDto.getStartTime())
                                .endTime(mentoringSessionTransactionDto.getEndTime())
                                .deadlineDate(mentoringSessionTransactionDto.getDeadlineDate())
                                .minHeadCount(mentoringSessionTransactionDto.getMinHeadCount())
                                .maxHeadCount(mentoringSessionTransactionDto.getMaxHeadCount())
                                .price(mentoringSessionTransactionDto.getPrice())
                                .isClosed(mentoringSessionTransactionDto.getIsClosed())
                                .isDeleted(mentoringSessionTransactionDto.getIsDeleted())
                                .build()
                ).toList();
    }
}
