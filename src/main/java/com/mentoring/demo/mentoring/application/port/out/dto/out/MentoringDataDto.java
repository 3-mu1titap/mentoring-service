package com.mentoring.demo.mentoring.application.port.out.dto.out;

import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MentoringDataDto {

    List<MentoringD> mentoringAddRequestDtoList;

    @Builder
    public MentoringDataDto(List<MentoringAddRequestDto> mentoringAddRequestDtoList) {
        this.mentoringAddRequestDtoList = mentoringAddRequestDtoList;
    }

    public static MentoringDataDto of(List<MentoringAddRequestDto> mentoringAddRequestDtoList) {
        return MentoringDataDto.builder()
               .mentoringAddRequestDtoList(mentoringAddRequestDtoList)
               .build();
    }

}
