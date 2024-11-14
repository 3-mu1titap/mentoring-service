package com.mentoring.demo.mentoring.adaptor.in.web.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class MentoringEditRequestVo {
    @Schema(description = "변경할 멘토링 UUID", example = "멘토링 UUID")
    private String uuid;
    @Schema(description = "변경할 멘토링 이름", example = "변경된 멘토링 이름")
    private String name;
    @Schema(description = "변경할 멘토링 설명", example = "변경된 멘토링 설명")
    private String description;
    @Schema(description = "변경할 멘토링 상세정보", example = "변경된 멘토링 상세정보")
    private String detail;

    @Schema(description = "변경할 멘토링 내용 재사용 여부", example = "false")
    private Boolean isReusable;

    @Schema(description = "변경할 멘토링 쌈네일 url", example = "https://google.com")
    private String thumbnailUrl;

    private List<MentoringCategoryVo> categoryList;

}
