package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.application.port.out.dto.out.DeadlinePastSessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringEditRequestOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionCreatedAfterOutDto;

public interface SendMessageUseCase {
    // 멘토링 생성 이벤트
    void sendCreateMentoringMessage(String topic , MentoringAddAfterOutDto mentoringAddAfterOutDto);

    // 멘토링 업데이트 이벤트
    void sendUpdateMentoringMessage(String topic , MentoringEditRequestOutDto mentoringEditRequestDto);

    // 세션 추가 이벤트
    void sendAddSessionMessage(String topic , SessionCreatedAfterOutDto sessionCreatedAfterOutDto);

    void sendDeadlinePastSession(String topic , DeadlinePastSessionResponseOutDto deadlinePastSessionResponseOutDto);

}
