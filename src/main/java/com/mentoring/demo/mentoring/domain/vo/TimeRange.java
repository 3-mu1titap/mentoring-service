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

    /**
     * 두 시간대가 겹치는지 확인
     */
    public boolean overlapsWith(TimeRange other) {
        // 같은 날의 시간대 비교
        boolean sameDayOverlap = this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);

        // 다음 날로 넘어가는 경우 비교
        if (this.isNextDay) {
            boolean nextDayOverlap = LocalTime.MIN.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);
            return sameDayOverlap || nextDayOverlap;
        }

        if (other.isNextDay) {
            boolean otherNextDayOverlap = this.startTime.isBefore(other.endTime) && LocalTime.MIN.isBefore(this.endTime);
            return sameDayOverlap || otherNextDayOverlap;
        }

        return sameDayOverlap;
    }

    /**
     * 리스트 내 시간대 중 겹침이 있는지 확인
     */
    public static boolean hasOverlappingTimeRanges(List<TimeRange> timeRanges) {
        for (int i = 0; i < timeRanges.size() - 1; i++) {
            for (int j = i + 1; j < timeRanges.size(); j++) {
                if (timeRanges.get(i).overlapsWith(timeRanges.get(j))) {
                    return true; // 겹침 발견
                }
            }
        }
        return false;
    }
}
