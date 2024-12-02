package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.in.*;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringCategoryAfterOutDto;

import java.util.List;

public interface MentoringRepositoryOutPort {
    MentoringAddAfterOutDto createMentoring(MentoringAddRequestOutDto mentoringAddRequestOutDto);

    List<MentoringCategoryAfterOutDto> createMentoringCategory(MentoringAddAfterOutDto mentoringAddAfterOutDto,
                                                               MentoringAddRequestOutDto mentoringAddRequestOutDto);



    List<MentoringCategoryAfterOutDto>  updateMentoring(MentoringEditRequestOutDto mentoringEditRequestOutDto);

    MentoringResponseOutDto findByMentoringUuid(String mentoringUuid);

    void deleteMentoringCategory(String mentoringId);
}
