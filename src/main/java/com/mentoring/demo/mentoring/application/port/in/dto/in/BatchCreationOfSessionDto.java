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

    private LocalDate deadLineDate; // 일괄생성마감일
    private List<TimeSlotDto> timeSlotDtoList;
}
