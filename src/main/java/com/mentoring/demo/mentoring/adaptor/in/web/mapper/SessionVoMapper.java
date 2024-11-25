package com.mentoring.demo.mentoring.adaptor.in.web.mapper;

import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.BatchCreationOfSessionVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.SessionAddRequestVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.SessionValidationRequestVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.TimeRangeVo;
import com.mentoring.demo.mentoring.application.port.in.dto.in.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SessionVoMapper {
    public static SessionValidationRequestDto of(String userUuid, SessionValidationRequestVo vo){
        return SessionValidationRequestDto.builder()
                .startDate(vo.getStartDate())
                .endDate(vo.getEndDate())
                .startTime(vo.getStartTime())
                .endTime(vo.getEndTime())
                .mentorUuid(userUuid)
                .build();
    }

    public static List<AddMentoringSessionDto> from(List<SessionAddRequestVo> voList){
        return voList.stream().map(
                vo -> AddMentoringSessionDto.builder()
                        .startDate(vo.getStartDate())
                        .endDate(vo.getEndDate())
                        .startTime(vo.getStartTime())
                        .endTime(vo.getEndTime())
                        .deadlineDate(vo.getDeadlineDate())
                        .minHeadCount(vo.getMinHeadCount())
                        .maxHeadCount(vo.getMaxHeadCount())
                        .price(vo.getPrice())
                        .build()
        ).toList();
    }

    public static BatchCreationOfSessionDto from (BatchCreationOfSessionVo vo){
        return BatchCreationOfSessionDto.builder()
                .mentoringUuid(vo.getMentoringUuid())
                .deadLineDate(vo.getDeadLineDate())
                .timeSlotDtoList(
                        vo.getTimeSlotVoList().stream()
                                .map(timeSlotVo -> TimeSlotDto.builder()
                                                    .dayOfWeek(timeSlotVo.getDayOfWeek())
                                                    .timeRanges(
                                                            timeSlotVo.getTimeRangeVos().stream()
                                                                    .map(timeRangeVo -> TimeRangeDto.builder()
                                                                            .startTime(LocalTime.parse(timeRangeVo.getStartTime()))
                                                                            .endTime(LocalTime.parse(timeRangeVo.getEndTime()))
                                                                            .build()
                                                                    ).toList()
                                                    )
                                        .build())
                                .toList()
                )
                .build();
    }
}
