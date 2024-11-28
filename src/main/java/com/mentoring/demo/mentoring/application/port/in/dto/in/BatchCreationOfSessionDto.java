package com.mentoring.demo.mentoring.application.port.in.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchCreationOfSessionDto {
    private String mentoringUuid;
    //private String mentorUuid;
    private LocalDate creationStartDate; // 일괄생성시작일
    private LocalDate creationEndDate; // 일괄생성마감일
    private List<TimeRangeDto> timeRanges;
    //private TimeSlotDto timeSlot;
}
