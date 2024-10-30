package com.mentoring.demo.mentoring.application.port.out.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentoringResponseOutDto {
    private String id;
    private String name;
    private String detail;

    private Boolean isReusable;
    private String thumbnailUrl;
}
