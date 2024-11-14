package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringEditRequestDto;

public interface MentoringUseCase {
    // 멘토링+세션 생성
    void createMentoringWithSession(MentoringAddRequestDto mentoringAddRequestDto);

    // 멘토링 정보 수정 (열어놓은 세션 수정 불가 X 멘토링 기본 정보만 변경)
    void updateMentoring(MentoringEditRequestDto mentoringEditRequestDto);
}
