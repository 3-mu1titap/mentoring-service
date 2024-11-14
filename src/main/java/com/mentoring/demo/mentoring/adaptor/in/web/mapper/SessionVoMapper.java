package com.mentoring.demo.mentoring.adaptor.in.web.mapper;

import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.SessionValidationRequestVo;
import com.mentoring.demo.mentoring.application.port.in.dto.in.SessionValidationRequestDto;
import org.springframework.stereotype.Component;

@Component
public class SessionVoMapper {
    public static SessionValidationRequestDto from(SessionValidationRequestVo vo){
        return SessionValidationRequestDto.builder()
                .startDate(vo.getStartDate())
                .endDate(vo.getEndDate())
                .startTime(vo.getStartTime())
                .endTime(vo.getEndTime())
                .mentorUuid(vo.getMentorUuid())
                .build();
    }

}
