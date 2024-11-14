package com.mentoring.demo.mentoring.adaptor.in.web.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class MentoringAddRequestVo {
    @Schema(description = "멘토링 이름", example = "멘토링 이름")
    private String name;
    @Schema(description = "멘토링 설명", example = "멘토링 설명")
    private String description;
    @Schema(description = "멘토링 상세", example = "멘토링 상세")
    private String detail;
    @Schema(description = "멘토 UUID", example = "멘토 UUID")
    private String mentorUuid;
    @Schema(description = "멘토링 상세정보 내용 재사용 여부")
    private Boolean isReusable;
    @Schema(description = "멘토링 썸네일 이미지" , example = "https://www.naver.com")
    private String thumbnailUrl;

    private List<MentoringTimeVo> sessionList;
    private List<MentoringCategoryVo> categoryList;


}
