package com.mentoring.demo.mentoring.application.port.out.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
}
