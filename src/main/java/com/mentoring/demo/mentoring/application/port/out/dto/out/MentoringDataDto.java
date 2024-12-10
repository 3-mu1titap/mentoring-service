package com.mentoring.demo.mentoring.application.port.out.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MentoringDataDto {

    List<String> mentoringUuid;
    List<String> sessionUuid;

    @Builder
    public MentoringDataDto(List<String> mentoringUuid, List<String> sessionUuid){
        this.mentoringUuid = mentoringUuid;
        this.sessionUuid = sessionUuid;
    }

    public static MentoringDataDto of(List<String> mentoringUuid, List<String> sessionUuid){
        return MentoringDataDto.builder()
                .mentoringUuid(mentoringUuid)
                .sessionUuid(sessionUuid)
                .build();
    }

}
