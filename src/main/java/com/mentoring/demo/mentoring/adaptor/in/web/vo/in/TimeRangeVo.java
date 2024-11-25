package com.mentoring.demo.mentoring.adaptor.in.web.vo.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

import java.time.LocalTime;
@Getter
@Setter
@ParameterObject
public class TimeRangeVo {
    @Schema(description = "시작 시간", example = "11:20:00")
    private String startTime;

    @Schema(description = "종료 시간", example = "12:20:00")
    private String endTime;

    @Schema(description = "최소 인원", example = "1")
    private Integer minHeadCount;

    @Schema(description = "최대 인원", example = "5")
    private Integer maxHeadCount;

    @Schema(description = "가격", example = "100")
    private Integer price;
}
