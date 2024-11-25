package com.mentoring.demo.mentoring.domain.vo;

import com.mentoring.demo.mentoring.common.entity.BaseResponseStatus;
import com.mentoring.demo.mentoring.common.exception.BaseException;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class TimeRange {
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean isNextDay; // 다음날로 넘어가는지 여부

    private Integer minHeadCount;
    private Integer maxHeadCount;
    private Integer price;

}
