package com.mentoring.demo.mentoring.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MentoringAddRequestDto{
    private String name;
    private String detail;
    private String mentorUuid;
    private Boolean isReusable;
    private String thumbnailUrl;

    private List<MentoringSessionDto> sessionList;
    private List<MentoringCategoryDto> categoryList;

    @Builder
    public MentoringAddRequestDto(String name, String detail, String mentorUuid, Boolean isReusable,
                                  String thumbnailUrl, List<MentoringSessionDto> sessionList,
                                  List<MentoringCategoryDto> categoryList) {
        this.name = name;
        this.detail = detail;
        this.mentorUuid = mentorUuid;
        this.isReusable = isReusable;
        this.thumbnailUrl = thumbnailUrl;
        this.sessionList = sessionList;
        this.categoryList = categoryList;
    }
}
