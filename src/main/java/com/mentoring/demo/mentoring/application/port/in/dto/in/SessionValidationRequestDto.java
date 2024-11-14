package com.mentoring.demo.mentoring.application.port.in.dto.in;

import com.mentoring.demo.mentoring.application.port.out.dto.in.SessionValidationRequestOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionValidationRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String mentorUuid;

    public SessionValidationRequestOutDto toOutDto() {
        return SessionValidationRequestOutDto.builder()
                .startDate(this.startDate)
                .endDate(this.endDate)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .mentorUuid(this.mentorUuid)
                .build();
    }
}
