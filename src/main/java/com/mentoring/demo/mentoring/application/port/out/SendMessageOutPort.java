package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringEditRequestOutDto;

public interface SendMessageOutPort {
    public void sendCreateMentoringMessage(String topic, MentoringAddAfterOutDto mentoringAddAfterOutDto);

    public void sendUpdateMentoringMessage(String topic, MentoringEditRequestOutDto mentoringEditRequestOutDto);


}
