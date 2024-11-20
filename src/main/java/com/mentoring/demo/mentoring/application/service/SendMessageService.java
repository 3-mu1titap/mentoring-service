package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.port.in.SendMessageUseCase;
import com.mentoring.demo.mentoring.application.port.out.SendMessageOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.out.DeadlinePastSessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringEditRequestOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionCreatedAfterOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class SendMessageService implements SendMessageUseCase {
    private final SendMessageOutPort sendMessageOutPort;


    @Override
    public void sendCreateMentoringMessage(String topic, MentoringAddAfterOutDto mentoringAddAfterOutDto) {
        sendMessageOutPort.sendCreateMentoringMessage(topic, mentoringAddAfterOutDto);
    }

    @Override
    public void sendUpdateMentoringMessage(String topic, MentoringEditRequestOutDto mentoringEditRequestOutDto) {
        sendMessageOutPort.sendUpdateMentoringMessage(topic, mentoringEditRequestOutDto);
    }

    @Override
    public void sendAddSessionMessage(String topic, SessionCreatedAfterOutDto sessionCreatedAfterOutDto) {
        sendMessageOutPort.sendAddSessionMessage(topic, sessionCreatedAfterOutDto);
    }

    @Override
    public void sendDeadlinePastSession(String topic, DeadlinePastSessionResponseOutDto deadlinePastSessionResponseOutDto) {
        sendMessageOutPort.sendDeadlinePastSessionMessage(topic, deadlinePastSessionResponseOutDto);
    }


}
