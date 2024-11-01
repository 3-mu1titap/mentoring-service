package com.mentoring.demo.mentoring.application.port.out.dto;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class MentoringSessionTransactionDto {

    private String uuid;

    private String mentoringId;
    private String mentoringUuid;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDateTime deadlineDatetime;

    private Integer minHeadCount;

    private Integer maxHeadCount;

    private Integer price;

    private Boolean isClosed;
    private Boolean isDeleted;

    
    @Builder
    public MentoringSessionTransactionDto(String uuid, String mentoringId, String mentoringUuid, LocalDate startDate,
                                          LocalDate endDate, LocalTime startTime, LocalTime endTime,
                                          LocalDateTime deadlineDatetime, Integer minHeadCount, Integer maxHeadCount,
                                          Integer price, Boolean isClosed, Boolean isDeleted) {
        this.uuid = uuid;
        this.mentoringId = mentoringId;
        this.mentoringUuid = mentoringUuid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deadlineDatetime = deadlineDatetime;
        this.minHeadCount = minHeadCount;
        this.maxHeadCount = maxHeadCount;
        this.price = price;
        this.isClosed = isClosed;
        this.isDeleted = isDeleted;
    }

    public static List<MentoringSessionEntity> toEntities(
            MentoringAddAfterOutDto mentoringAddAfterOutDto,
            List<MentoringSessionTransactionDto> mentoringSessionTransactionDtoList) {

        MentoringEntity mentoringEntity = MentoringEntity.builder()
                .id(Long.valueOf(mentoringAddAfterOutDto.getMentoringId()))
                .mentoringUuid(mentoringAddAfterOutDto.getMentoringUuid())
                .name(mentoringAddAfterOutDto.getName())
                .detail(mentoringAddAfterOutDto.getDetail())
                .mentorUuid(mentoringAddAfterOutDto.getMentorUuid())
                .thumbnailUrl(mentoringAddAfterOutDto.getThumbnailUrl())
                .isReusable(mentoringAddAfterOutDto.getIsReusable())
                .isDeleted(mentoringAddAfterOutDto.getIsDeleted())
                .build();

        return mentoringSessionTransactionDtoList.stream()
                .map(
                    mentoringSessionTransactionDto
                            -> MentoringSessionEntity.builder()
                                .uuid(mentoringSessionTransactionDto.getUuid())
                                .mentoringEntity(mentoringEntity)
                                .startDate(mentoringSessionTransactionDto.getStartDate())
                                .endDate(mentoringSessionTransactionDto.getEndDate())
                                .startTime(mentoringSessionTransactionDto.getStartTime())
                                .endTime(mentoringSessionTransactionDto.getEndTime())
                                .deadlineDatetime(mentoringSessionTransactionDto.getDeadlineDatetime())
                                .minHeadCount(mentoringSessionTransactionDto.getMinHeadCount())
                                .maxHeadCount(mentoringSessionTransactionDto.getMaxHeadCount())
                                .price(mentoringSessionTransactionDto.getPrice())
                                .isClosed(mentoringSessionTransactionDto.getIsClosed())
                                .isDeleted(mentoringSessionTransactionDto.getIsDeleted())
                                .build()
                ).toList();
    }
}
