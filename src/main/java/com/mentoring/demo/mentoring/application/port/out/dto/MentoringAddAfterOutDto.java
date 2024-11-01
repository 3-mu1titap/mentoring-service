package com.mentoring.demo.mentoring.application.port.out.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MentoringAddAfterOutDto {
    private String mentoringId;

    private String mentoringUuid;

    private String name;

    private String detail;

    private String mentorUuid;

    private String thumbnailUrl;

    private Boolean isReusable;

    private Boolean isDeleted;

    //private List<MentoringSessionAddAfterOutDto> mentoringSessionAddAfterOutDtoList;
}
