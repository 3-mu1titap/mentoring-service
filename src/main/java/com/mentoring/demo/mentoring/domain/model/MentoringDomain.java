package com.mentoring.demo.mentoring.domain.model;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringResponseOutDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class MentoringDomain {
    private String id;
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
    public MentoringDomain(String id, String uuid, String name, String detail, String mentorUuid, String thumbnailUrl,
                           Boolean isReusable, Boolean isDeleted,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
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

    public static MentoringDomain createMentoring(MentoringAddRequestDto mentoringAddRequestDto, String uuid) {
        return MentoringDomain.builder()
                .uuid(uuid) // 멘토링 UUID 생성
                .name(mentoringAddRequestDto.getName())
                .detail(mentoringAddRequestDto.getDetail())
                .mentorUuid(mentoringAddRequestDto.getMentorUuid())
                .thumbnailUrl(mentoringAddRequestDto.getThumbnailUrl())
                .isReusable(mentoringAddRequestDto.getIsReusable())
                .isDeleted(false)
                .build();
    }

    public static MentoringDomain updateMentoring(MentoringEditRequestDto mentoringEditRequestDto) {
        return MentoringDomain.builder()
                .uuid(mentoringEditRequestDto.getUuid())
                .name(mentoringEditRequestDto.getName())
                .detail(mentoringEditRequestDto.getDetail())
                .thumbnailUrl(mentoringEditRequestDto.getThumbnailUrl())
                .isReusable(mentoringEditRequestDto.getIsReusable())
                .build();
    }

    // 서비스에서 포트에서 조회한 값을 받고, 프론트에서 받은 DTO를 도메인에서 작성
    public static MentoringDomain updateMentoring2(
            MentoringEditRequestDto editDto , MentoringResponseOutDto mentoringResponseOutDto)
    {
            return MentoringDomain.builder()
                .id(mentoringResponseOutDto.getId())
                .uuid(mentoringResponseOutDto.getUuid())
                .mentorUuid(mentoringResponseOutDto.getMentorUuid())
                .name(editDto.getName())
                .detail(editDto.getDetail())
                .thumbnailUrl(editDto.getThumbnailUrl())
                .isReusable(editDto.getIsReusable())
                .isDeleted(mentoringResponseOutDto.getIsDeleted())
                .build();
    }
}
