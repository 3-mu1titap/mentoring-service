package com.mentoring.demo.mentoring.adaptor.in.web.vo.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

import java.time.DayOfWeek;
import java.util.List;
@Getter
public class TimeSlotVo {
    private List<TimeRangeVo> timeRangeVos;
}
