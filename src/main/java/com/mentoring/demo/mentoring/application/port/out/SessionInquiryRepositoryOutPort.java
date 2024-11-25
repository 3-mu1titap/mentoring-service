package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;

public interface SessionInquiryRepositoryOutPort {

    boolean existsMentoringSession(String mentoringId, MentoringSessionOutDto mentoringSessionOutDto);

}
