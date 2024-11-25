package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.port.in.MentoringInquiryUseCase;
import com.mentoring.demo.mentoring.application.port.out.MentoringInquiryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class MentoringInquiryService implements MentoringInquiryUseCase {
    private final MentoringInquiryOutPort mentoringInquiryOutPort;
    @Override
    public String getMentoringIdByMentoringUuid(String mentoringUuid) {
        return mentoringInquiryOutPort.getMentoringIdByMentoringUuid(mentoringUuid);
    }

}
