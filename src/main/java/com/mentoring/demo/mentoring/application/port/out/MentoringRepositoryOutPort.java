package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.MentoringTransactionDto;

public interface MentoringRepositoryOutPort {
    void createMentoring(MentoringTransactionDto mentoringTransactionDto);
}
