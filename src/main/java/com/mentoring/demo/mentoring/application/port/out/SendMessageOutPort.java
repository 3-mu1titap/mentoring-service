package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringEditRequestOutDto;

public interface SendMessageOutPort {
    public void sendCreateMentoringMessage(String topic, MentoringAddAfterOutDto mentoringAddAfterOutDto);

    public void sendUpdateMentoringMessage(String topic, MentoringEditRequestOutDto mentoringEditRequestOutDto);


}
