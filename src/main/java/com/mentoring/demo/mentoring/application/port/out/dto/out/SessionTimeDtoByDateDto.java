package com.mentoring.demo.mentoring.application.port.out.dto.out;


import com.mentoring.demo.mentoring.application.port.in.dto.in.TimeRangeDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SessionTimeDtoByDateDto {
//    private Map<>
    private LocalDate startDate;
    private List<TimeRangeDto> timeRanges;
}
