package com.mentoring.demo.mentoring.application.port.out.dto;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MentoringEditTransactionDto {
    private String uuid;
    private String name;
    private String detail;

    private Boolean isReusable;
    private String thumbnailUrl;

    @Builder
    public MentoringEditTransactionDto(String uuid, String name, String detail, Boolean isReusable, String thumbnailUrl) {
        this.uuid = uuid;
        this.name = name;
        this.detail = detail;
        this.isReusable = isReusable;
        this.thumbnailUrl = thumbnailUrl;
    }

    public MentoringEntity toEntity() {
        return MentoringEntity.builder()
                .mentoringUuid(this.uuid)
                .name(this.name)
                .detail(this.detail)
                .thumbnailUrl(this.thumbnailUrl)
                .isReusable(this.isReusable)
                .build();
    }
}
