package com.mentoring.demo.mentoring.application.port.out.dto;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@ToString
public class MentoringSessionTransactionDto {

    private String uuid;

    //private MentoringEntity mentoringEntity;
    private Long mentoringId;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDateTime deadlineDatetime;

    private Integer minHeadCount;

    private Integer maxHeadCount;

    private Integer price;

    private Boolean isClosed;

    
    @Builder
    public MentoringSessionTransactionDto(String uuid, Long mentoringId,
                                          LocalDate startDate, LocalDate endDate,
                                          LocalTime startTime, LocalTime endTime, LocalDateTime deadlineDatetime,
                                          Integer minHeadCount, Integer maxHeadCount, Integer price, Boolean isClosed) {
        this.uuid = uuid;
        this.mentoringId = mentoringId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deadlineDatetime = deadlineDatetime;
        this.minHeadCount = minHeadCount;
        this.maxHeadCount = maxHeadCount;
        this.price = price;
        this.isClosed = isClosed;
    }
}
