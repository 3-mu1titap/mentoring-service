package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;

public interface MentoringSessionUseCase {

    // 멘토링 시간 생성
    void createSession();
}
