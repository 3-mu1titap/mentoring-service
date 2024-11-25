package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.DeadlinePastSessionResponseOutDto;
import com.mentoring.demo.mentoring.domain.model.MentoringSessionDomain;

import java.time.LocalDate;
import java.util.List;

public interface MentoringInquiryOutPort {
    String getMentoringIdByMentoringUuid(String mentoringUuid);
    MentoringResponseOutDto getMentoringResponseByMentoringUuid(String mentoringUuid);


}
