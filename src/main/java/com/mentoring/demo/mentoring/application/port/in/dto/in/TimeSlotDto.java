package com.mentoring.demo.mentoring.application.port.in.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlotDto {
    private DayOfWeek dayOfWeek;
    private List<TimeRangeDto> timeRanges;
    //private Map<DayOfWeek, List<TimeRangeDto>> timeSlots;

}
