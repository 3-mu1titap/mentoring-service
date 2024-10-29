package com.mentoring.demo.mentoring.domain.model;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class MentoringDomain {
    private String uuid;
    private String name;
    private String detail;
    private String mentorUuid;
    private String thumbnailUrl;

    private Boolean isReusable;
    private Boolean isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public MentoringDomain(String uuid, String name, String detail, String mentorUuid, String thumbnailUrl,
                           Boolean isReusable, Boolean isDeleted,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.uuid = uuid;
        this.name = name;
        this.detail = detail;
        this.mentorUuid = mentorUuid;
        this.thumbnailUrl = thumbnailUrl;
        this.isReusable = isReusable;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static MentoringDomain createMentoring(MentoringAddRequestDto mentoringAddRequestDto) {
        return MentoringDomain.builder()
                .uuid(UUID.randomUUID().toString()) // 멘토링 UUID 생성
                .name(mentoringAddRequestDto.getName())
                .detail(mentoringAddRequestDto.getDetail())
                .mentorUuid(mentoringAddRequestDto.getMentorUuid())
                .thumbnailUrl(mentoringAddRequestDto.getThumbnailUrl())
                .isReusable(mentoringAddRequestDto.getIsReusable())
                .isDeleted(false)
                .build();
    }

}
