package com.mentoring.demo.mentoring.adaptor.in.web.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MentoringCategoryVo {
    @Schema(description = "대 카테고리 코드" , example = "TC-B7B89EE1")
    private String topCategoryCode;
    @Schema(description = "중 카테고리 코드", example = "MC-9F1EA6F9")
    private String middleCategoryCode;
    @Schema(description = "소 카테고리 코드", nullable = true)
    private String bottomCategoryCode;

    @Schema(description = "대 카테고리 이름", example = "테스트 대 카테고리")
    private String topCategoryName;
    @Schema(description = "중 카테고리 이름", example = "테스트 중 카테고리")
    private String middleCategoryName;
    @Schema(description = "하 카테고리 이름", nullable = true)
    private String bottomCategoryName;
}
