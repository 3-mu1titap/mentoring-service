package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.port.in.SessionInquiryUseCase;
import com.mentoring.demo.mentoring.application.port.out.MentoringSessionRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.SessionInquiryRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.DeadlinePastSessionResponseOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional
public class SessionInquiryService implements SessionInquiryUseCase {
    private final MentoringSessionRepositoryOutPort mentoringSessionRepositoryOutPort;
    private final SessionInquiryRepositoryOutPort sessionInquiryRepositoryOutPort;
    @Override
    public List<DeadlinePastSessionResponseOutDto> getPastDeadlineSessions() {
        LocalDate now = LocalDate.now().minusDays(1);
        return mentoringSessionRepositoryOutPort.getPastDeadlineSessions(now);
    }

    @Override
    public boolean existsMentoringSession(String mentoringId, MentoringSessionOutDto mentoringSessionOutDto) {
        return sessionInquiryRepositoryOutPort.existsMentoringSession(mentoringId,mentoringSessionOutDto);
    }


}
