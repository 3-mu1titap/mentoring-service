package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.adaptor.in.web.vo.MentoringAddRequestVo;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;

public interface MentoringUseCase {
    // 멘토링 생성
    void createMentoring(MentoringAddRequestDto mentoringAddRequestDto);

    // 멘토링 정보 수정 (열어놓은 스케줄 X 멘토링 기본 정보만 변경)
    void updateMentoring(MentoringEditRequestDto mentoringEditRequestDto);
}
