package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.MentoringEditTransactionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringTransactionDto;

import java.util.Optional;

public interface MentoringRepositoryOutPort {
    void createMentoring(MentoringTransactionDto mentoringTransactionDto);

    void updateMentoring(MentoringEditTransactionDto mentoringEditTransactionDto);

}
