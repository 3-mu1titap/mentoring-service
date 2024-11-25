package com.mentoring.demo.mentoring.application.port.out.dto.out;

import lombok.*;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TimeRangeOutDto {
    private LocalTime startTime;
    private LocalTime endTime;
}
