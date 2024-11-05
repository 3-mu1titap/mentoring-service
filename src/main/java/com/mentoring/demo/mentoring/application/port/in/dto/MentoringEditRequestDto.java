package com.mentoring.demo.mentoring.application.port.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MentoringEditRequestDto{

    private String uuid;
    private String name;
    private String detail;

    private Boolean isReusable;
    private String thumbnailUrl;

    private List<MentoringCategoryDto> categoryList;

}
