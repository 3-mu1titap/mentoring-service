package com.mentoring.demo.mentoring.application.port.out.dto.out;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SessionResponseOutDto {
    private Integer maxHeadCount;
    private LocalDate deadlineDate;
    private Boolean isClosed;

    public static SessionResponseOutDto from(MentoringSessionEntity entity){
        return SessionResponseOutDto.builder()
                .maxHeadCount(entity.getMaxHeadCount())
                .deadlineDate(entity.getDeadlineDate())
                .isClosed(entity.getIsClosed())
                .build();
    }
}
