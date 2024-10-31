package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddAfterDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddTransactionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringEditTransactionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringResponseOutDto;

public interface MentoringRepositoryOutPort {
    MentoringAddAfterOutDto createMentoring(MentoringAddTransactionDto mentoringTransactionDto);

    void updateMentoring(MentoringEditTransactionDto mentoringEditTransactionDto);

    MentoringResponseOutDto findByMentoringUuid(String mentoringUuid);

}
