package com.mentoring.demo.mentoring.application.port.out;

import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringHashtagOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringCategoryAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringHashTagAfterOutDto;

import java.util.List;

public interface MentoringHashtagRepositoryOutPort {
    MentoringHashTagAfterOutDto createMentoringHashtag(MentoringAddAfterOutDto mentoringAddAfterOutDto, List<MentoringHashtagOutDto> MentoringHashtagOutDto);


    void deleteMentoringHashtag(String mentoringId);
}
