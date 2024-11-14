package com.mentoring.demo.mentoring.adaptor.in.web.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@ParameterObject
public class SessionValidationRequestVo {
    @Schema(description = "시작날짜 ex) 2024-11-15 " ,example = "2024-11-15")
    private LocalDate startDate;
    @Schema(description = "종료날짜 ex) 2024-11-15 " ,example = "2024-11-15")
    private LocalDate endDate;
    @Schema(description = "멘토링 세션 시작시간", example = "13:00:00")
    private LocalTime startTime;
    @Schema(description = "멘토링 세션 종료시간", example = "14:00:00")
    private LocalTime endTime;
    @Schema(description = "멘토 uuid")
    private String mentorUuid;
}
