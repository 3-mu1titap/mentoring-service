package com.mentoring.demo.mentoring.application.port.out.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentoringHashtagOutDto {
    private String hashtagId;
    private String hashtagName;
}
