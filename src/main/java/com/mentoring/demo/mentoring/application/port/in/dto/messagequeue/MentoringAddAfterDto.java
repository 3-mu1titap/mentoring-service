package com.mentoring.demo.mentoring.application.port.in.dto.messagequeue;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "mentoringSessionAddAfterDtoList")
//@ToString
public class MentoringAddAfterDto {
    private String mentoringId;

    private String mentoringUuid;

    private String name;

    private String detail;

    private String mentorUuid;

    private String thumbnailUrl;

    private Boolean isReusable;

    private Boolean isDeleted;

    @Setter
    private List<MentoringSessionAddAfterDto> mentoringSessionAddAfterDtoList;

}
