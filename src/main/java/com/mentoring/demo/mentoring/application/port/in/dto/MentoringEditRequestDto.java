package com.mentoring.demo.mentoring.application.port.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class MentoringEditRequestDto{

    private String uuid;
    private String name;
    private String detail;

    private Boolean isReusable;
    private String thumbnailUrl;

    @Builder
    public MentoringEditRequestDto(String uuid, String name, String detail, Boolean isReusable, String thumbnailUrl) {
        this.uuid = uuid;
        this.name = name;
        this.detail = detail;
        this.isReusable = isReusable;
        this.thumbnailUrl = thumbnailUrl;
    }
}
