package com.mentoring.demo.mentoring.application.port.in.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "mentoringSessionAddAfterDtoList")
public class MentoringAddAfterDto {
    private String mentoringId;

    private String mentoringUuid;

    private String name;

    private String detail;

    private String mentorUuid;

    private String thumbnailUrl;

    private Boolean isReusable;

    private Boolean isDeleted;

    private List<MentoringSessionAddAfterDto> mentoringSessionAddAfterDtoList;

}
