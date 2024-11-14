package com.mentoring.demo.mentoring.application.port.in.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MentoringAddRequestDto{
    private String name;
    private String description;
    private String detail;
    private String mentorUuid;
    private Boolean isReusable;
    private String thumbnailUrl;

    private List<MentoringSessionDto> sessionList;
    private List<MentoringCategoryDto> categoryList;
}
