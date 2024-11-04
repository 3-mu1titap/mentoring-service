package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddAfterOutDto;

public interface SendMessageOutPort {
    public void sendCreateMentoringMessage(String topic, MentoringAddAfterOutDto mentoringAddAfterOutDto);


}
