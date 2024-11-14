package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.port.in.SendMessageUseCase;
import com.mentoring.demo.mentoring.application.port.out.SendMessageOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringEditRequestOutDto;
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

}
