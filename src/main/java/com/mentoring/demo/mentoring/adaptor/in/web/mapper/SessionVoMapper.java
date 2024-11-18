package com.mentoring.demo.mentoring.adaptor.in.web.mapper;

import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.SessionAddRequestVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.SessionValidationRequestVo;
import com.mentoring.demo.mentoring.application.port.in.dto.in.AddMentoringSessionDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.SessionValidationRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

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

}
