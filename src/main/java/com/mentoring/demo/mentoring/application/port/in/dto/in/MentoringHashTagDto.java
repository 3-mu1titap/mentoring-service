package com.mentoring.demo.mentoring.application.port.in.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MentoringHashTagDto {
    private String hashtagId;
    private String hashtagName;
}
