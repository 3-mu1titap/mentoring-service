package com.mentoring.demo.mentoring.domain.model;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringSessionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddAfterOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MentoringSessionDomain {

    private String uuid;

    private String mentoringId;
    private String mentoringUuid;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate deadlineDate;

    private Integer price;
    private Integer minHeadCount;
    private Integer maxHeadCount;

    private Boolean isClosed;
    private Boolean isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
