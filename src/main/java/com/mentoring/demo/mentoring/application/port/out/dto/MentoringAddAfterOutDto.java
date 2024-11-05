package com.mentoring.demo.mentoring.application.port.out.dto;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddAfterDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringCategoryDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringSessionAddAfterDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"mentoringSessionAddAfterOutDtoList", "mentoringCategoryOutDtoList"})
public class MentoringAddAfterOutDto {
    private String mentoringId;

    private String mentoringUuid;

    private String name;

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
