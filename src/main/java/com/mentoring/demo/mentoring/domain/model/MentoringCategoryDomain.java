package com.mentoring.demo.mentoring.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MentoringCategoryDomain {
    private String id;
    private String mentoringUuid;
    private String topCategoryCode;
    private String middleCategoryCode;
    private String bottomCategoryCode;
    // 카테고리명
    private String topCategoryName;
    private String middleCategoryName;
    private String bottomCategoryName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
