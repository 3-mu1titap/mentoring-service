package com.mentoring.demo.mentoring.application.port.in.dto.in;

import com.mentoring.demo.mentoring.domain.vo.TimeRange;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TimeRangeDto {
    private LocalTime startTime;
    private LocalTime endTime;

    private Integer minHeadCount;
    private Integer maxHeadCount;
    private Integer price;

    private List<DayOfWeek> dayOfWeekList;

//    public TimeRange toTimeRange() {
//        // 시작 시간 > 종료 시간 이면 다음날로 판단
//        boolean isNextDay = this.startTime.isAfter(this.endTime);
//        return TimeRange.builder()
//                .startTime(this.startTime)
//                .endTime(this.endTime)
//                .isNextDay(isNextDay) // 다음날로 넘어가는 경우 설정
//                .minHeadCount(this.minHeadCount)
//                .maxHeadCount(this.maxHeadCount)
//                .price(this.price)
//                .build();
//    }
}
