package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;

public interface MentoringInquiryUseCase {
    String getMentoringIdByMentoringUuid(String mentoringUuid);

}
