package com.mentoring.demo.mentoring.adaptor.in.web.vo.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mentoring.demo.mentoring.application.port.in.dto.in.TimeSlotDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

import java.time.LocalDate;
import java.util.List;

@Getter
public class BatchCreationOfSessionVo {
    @Schema(description = "멘토링 UUID" , required = true)
    private String mentoringUuid;
    @Schema(description = "일괄생성 마감일 (yyyy-MM-dd)", required = true)
    private LocalDate deadLineDate; // 일괄생성마감일

    private List<TimeSlotVo> timeSlotVoList;
}
