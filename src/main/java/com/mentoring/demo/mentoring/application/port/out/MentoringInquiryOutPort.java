package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.domain.model.MentoringSessionDomain;

public interface MentoringInquiryOutPort {
    String getMentoringIdByMentoringUuid(String mentoringUuid);
    MentoringResponseOutDto getMentoringResponseByMentoringUuid(String mentoringUuid);


}
