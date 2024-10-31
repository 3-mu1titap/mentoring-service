package com.mentoring.demo.mentoring.application.port.out.dto;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MentoringEditTransactionDto {
    private String id;
    private String uuid;
    private String name;
    private String detail;
    private String mentorUuid;
    private Boolean isReusable;
    private Boolean isDeleted;
    private String thumbnailUrl;

    @Builder
    public MentoringEditTransactionDto(String id, String uuid, String name, String detail, String mentorUuid,
                                       Boolean isReusable, Boolean isDeleted, String thumbnailUrl) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.detail = detail;
        this.mentorUuid = mentorUuid;
        this.isReusable = isReusable;
        this.isDeleted = isDeleted;
        this.thumbnailUrl = thumbnailUrl;
    }
}
