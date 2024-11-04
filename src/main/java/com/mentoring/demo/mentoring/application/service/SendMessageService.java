package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.port.in.SendMessageUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.out.SendMessageOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringSessionOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class SendMessageService implements SendMessageUseCase {
    private final SendMessageOutPort sendMessageOutPort;


    @Override
    public void sendCreateMentoringMessage(String topic, MentoringAddAfterOutDto mentoringAddAfterOutDto) {
        sendMessageOutPort.sendCreateMentoringMessage(topic, mentoringAddAfterOutDto);
    }
}
