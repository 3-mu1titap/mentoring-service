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
    private LocalDate startDate; // 세션 참가 신청 쉬소 시 , 캘린더 read data 업데이트에 필요

    public static SessionResponseOutDto from(MentoringSessionEntity entity){
        return entity!=null ? SessionResponseOutDto.builder()
                .maxHeadCount(entity.getMaxHeadCount())
                .deadlineDate(entity.getDeadlineDate())
                .isClosed(entity.getIsClosed())
                .startDate(entity.getStartDate())
                .build() : null;
    }
}
