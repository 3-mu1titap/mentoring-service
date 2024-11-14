package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringEditRequestOutDto;

public interface SendMessageUseCase {
    void sendCreateMentoringMessage(String topic , MentoringAddAfterOutDto mentoringAddAfterOutDto);

    void sendUpdateMentoringMessage(String topic , MentoringEditRequestOutDto mentoringEditRequestDto);

}
