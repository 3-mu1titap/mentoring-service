package com.mentoring.demo.mentoring.application.port.in.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MentoringAddAfterDto implements Serializable {
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
