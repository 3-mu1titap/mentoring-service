package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.out.DeadlinePastSessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringEditRequestOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionCreatedAfterOutDto;

public interface SendMessageOutPort {
    public void sendCreateMentoringMessage(String topic, MentoringAddAfterOutDto mentoringAddAfterOutDto);

    public void sendUpdateMentoringMessage(String topic, MentoringEditRequestOutDto mentoringEditRequestOutDto);

    public void sendAddSessionMessage(String topic, SessionCreatedAfterOutDto sessionCreatedAfterOutDto);

    public void sendDeadlinePastSessionMessage(String topic, DeadlinePastSessionResponseOutDto deadlinePastSessionResponseOutDto);

}
