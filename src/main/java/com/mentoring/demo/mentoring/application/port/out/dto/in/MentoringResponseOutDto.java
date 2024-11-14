package com.mentoring.demo.mentoring.application.port.out.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentoringResponseOutDto {
    private String id;
    private String uuid;

    // 변경가능한 필드
    private String name;
    private String description;
    private String detail;
    private Boolean isReusable;
    private String thumbnailUrl;

    // 변경불가능한 필드
    private String mentorUuid;
    private Boolean isDeleted;
}
