package com.mentoring.demo.mentoring.application.port.out.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MentoringCategoryAfterOutDto {
    private String id;

    private String mentoringUuid;

    private String topCategoryCode;
    private String middleCategoryCode;
    private String bottomCategoryCode;
    // 카테고리명
    private String categoryName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
