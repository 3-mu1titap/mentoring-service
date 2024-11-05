package com.mentoring.demo.mentoring.application.port.out.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentoringCategoryOutDto {
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
