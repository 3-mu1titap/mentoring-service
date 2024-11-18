package com.mentoring.demo.mentoring.adaptor.in.web.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class SessionAddRequestVo {
    @Schema(description = "멘토링 세션 시작날짜", example = "2024-10-01")
    private LocalDate startDate;
    @Schema(description = "멘토링 세션 종료날짜", example = "2024-10-01")
    private LocalDate endDate;

    @Schema(description = "멘토링 세션 시작시간", example = "10:00:00")
    private LocalTime startTime;
    @Schema(description = "멘토링 세션 종료시간", example = "11:20:00")
    private LocalTime endTime;
    @Schema(description = "멘토링 세션 예약 마감 날짜시간", example = "2024-10-01")
    private LocalDate deadlineDate;

    @Schema(description = "멘토링 세션 최소인원", example = "1")
    private Integer minHeadCount;
    @Schema(description = "멘토링 세션 최대인원", example = "5")
    private Integer maxHeadCount;
    @Schema(description = "멘토링 세션 볼트(가격)", example = "100")
    private Integer price;
}
