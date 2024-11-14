package com.mentoring.demo.mentoring.application.port.in.dto.in;

import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringCategoryDto;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MentoringEditRequestDto{

    private String uuid;
    private String name;
    private String description;
    private String detail;

    private Boolean isReusable;
    private String thumbnailUrl;

    private List<MentoringCategoryDto> categoryList;

}
