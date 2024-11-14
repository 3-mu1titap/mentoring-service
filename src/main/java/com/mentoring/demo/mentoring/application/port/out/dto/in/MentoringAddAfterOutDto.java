package com.mentoring.demo.mentoring.application.port.out.dto.in;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"mentoringSessionAddAfterOutDtoList", "mentoringCategoryAfterOutDtoList"})
public class MentoringAddAfterOutDto {
    private String mentoringId;

    private String mentoringUuid;

    private String name;

    private String description;
    private String detail;

    private String mentorUuid;

    private String thumbnailUrl;

    private Boolean isReusable;

    private Boolean isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Setter
    private List<MentoringSessionAddAfterOutDto> mentoringSessionAddAfterOutDtoList;

    @Setter
    private List<MentoringCategoryAfterOutDto> mentoringCategoryAfterOutDtoList;

}
