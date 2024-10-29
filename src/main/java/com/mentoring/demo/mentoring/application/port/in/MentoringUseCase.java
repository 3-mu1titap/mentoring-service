package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.adaptor.in.web.vo.MentoringAddRequestVo;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;

public interface MentoringUseCase {
    // 멘토링 생성
    void createMentoring(MentoringAddRequestDto mentoringAddRequestDto);
}
