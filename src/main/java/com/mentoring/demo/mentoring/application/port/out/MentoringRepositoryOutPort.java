package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddAfterDto;
import com.mentoring.demo.mentoring.application.port.out.dto.*;

import java.util.List;

public interface MentoringRepositoryOutPort {
    MentoringAddAfterOutDto createMentoring(MentoringAddTransactionDto mentoringTransactionDto);

    void createMentoringSession(MentoringAddAfterOutDto mentoringAddAfterOutDto,List<MentoringSessionTransactionDto> sessionTransactionDto);

    void updateMentoring(MentoringEditTransactionDto mentoringEditTransactionDto);

    MentoringResponseOutDto findByMentoringUuid(String mentoringUuid);

}
