package com.mentoring.demo.mentoring.adaptor.in.web.vo.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MentoringHashTagVo {
    @Schema(description = "해시태그 ID")
    private String hashtagId;
    @Schema(description = "해시태그 이름")
    private String hashtagName;
}
