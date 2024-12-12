package com.mentoring.demo.mentoring.application.port.out.dto.out;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SessionTimeResponseOutDto {
    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime startTime;
    private LocalTime endTime;

    public static SessionTimeResponseOutDto from(MentoringSessionEntity entity) {
        return entity != null ?SessionTimeResponseOutDto.builder()
                .startDate(entity.getStartDate())
                .startTime(entity.getStartTime())
                .endDate(entity.getEndDate())
                .endTime(entity.getEndTime())
                .build() : null;
    }
}
