package com.mentoring.demo.mentoring.domain.model;

import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringResponseOutDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentoringDomain {
    private String id;
    private String uuid;
    private String name;
    private String description;
    private String detail;
    private String mentorUuid;
    private String thumbnailUrl;

    private Boolean isReusable;
    private Boolean isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<MentoringSessionDomain> mentoringSessions;
    private List<MentoringCategoryDomain> mentoringCategories;
    private List<MentoringHashtagDomain> mentoringHashtags;


    // 도메인 로직: 오늘 날짜에 시작하는 세션이 있는지 검증
    public void checkForTodayStartSessions() {
        LocalDate today = LocalDate.now();
        if(mentoringSessions != null){
            for (MentoringSessionDomain session : mentoringSessions) {
                if (session.getStartDate().equals(today)) {
                    throw new IllegalArgumentException("오늘 날짜에 시작하는 멘토링 세션이 있습니다.");
                }
            }
        }

    }
    // 업데이트 체크
    public static void checkUpdateObject(MentoringResponseOutDto mentoringResponseOutDto) {
        if(mentoringResponseOutDto == null){
            throw new IllegalArgumentException("업데이트 하려는 멘토링이 존재하지 않습니다.");
        }
    }

    public static MentoringDomain createMentoring(MentoringAddRequestDto dto, String uuid) {
        return MentoringDomain.builder()
                .uuid(uuid) // 멘토링 UUID 생성
                .name(dto.getName())
                .description(dto.getDescription())
                .detail(dto.getDetail())
                .mentorUuid(dto.getMentorUuid())
                .thumbnailUrl(dto.getThumbnailUrl())
                .isReusable(dto.getIsReusable())
                .isDeleted(false)
                .mentoringSessions( // 멘토링 세션
                        dto.getSessionList() != null ? dto.getSessionList().stream()
                        .map(
                                session -> MentoringSessionDomain.builder()
                                            .uuid(UUID.randomUUID().toString())
                                            .mentoringUuid(uuid)
                                            .startDate(session.getStartDate())
                                            .endDate(session.getEndDate())
                                            .startTime(session.getStartTime())
                                            .endTime(session.getEndTime())
                                            .deadlineDate(session.getDeadlineDate())
                                            .price(session.getPrice())
                                            .minHeadCount(session.getMinHeadCount())
                                            .maxHeadCount(session.getMaxHeadCount())
                                            .isClosed(false)
                                            .isDeleted(false)
                                            .build()
                        ).toList() : null
                )
                .mentoringCategories( // 멘토링 카테고리
                        dto.getCategoryList() != null ? dto.getCategoryList().stream()
                        .map(
                                category -> MentoringCategoryDomain.builder()
                                                .mentoringUuid(uuid)
                                                .topCategoryCode(category.getTopCategoryCode())
                                                .middleCategoryCode(category.getMiddleCategoryCode())
                                                .bottomCategoryCode(category.getBottomCategoryCode())
                                                .topCategoryName(category.getTopCategoryName())
                                                .middleCategoryName(category.getMiddleCategoryName())
                                                .bottomCategoryName(category.getBottomCategoryName())
                                                .build()
                        ).toList() : null
                )
                .mentoringHashtags( // 멘토링 해시태그
                        dto.getHashTagList() != null ? dto.getHashTagList().stream()
                        .map(
                                hashtag -> MentoringHashtagDomain.builder()
                                        .mentoringUuid(uuid)
                                        .hashtagId(hashtag.getHashtagId())
                                        .hashtagName(hashtag.getHashtagName())
                                        .build()
                        ).toList() : null
                )
                .build();
    }

    // 서비스에서 포트에서 조회한 값을 받고, 프론트에서 받은 DTO를 도메인에서 작성
    public static MentoringDomain updateMentoring(
            MentoringEditRequestDto editDto , MentoringResponseOutDto mentoringResponseOutDto)
    {
            return MentoringDomain.builder()
                .id(mentoringResponseOutDto.getId())
                .uuid(mentoringResponseOutDto.getUuid())
                .mentorUuid(mentoringResponseOutDto.getMentorUuid())
                .name(editDto.getName())
                .description(editDto.getDescription())
                .detail(editDto.getDetail())
                .thumbnailUrl(editDto.getThumbnailUrl())
                .isReusable(editDto.getIsReusable())
                .isDeleted(mentoringResponseOutDto.getIsDeleted())
                .mentoringCategories( // 멘토링 카테고리
                        editDto.getCategoryList() != null ? editDto.getCategoryList().stream()
                                .map(
                                        category -> MentoringCategoryDomain.builder()
                                                .mentoringUuid(mentoringResponseOutDto.getUuid())
                                                .topCategoryCode(category.getTopCategoryCode() != null ? category.getTopCategoryCode(): null)
                                                .middleCategoryCode(category.getMiddleCategoryCode() != null ? category.getMiddleCategoryCode(): null)
                                                .bottomCategoryCode(category.getBottomCategoryCode() != null ? category.getBottomCategoryCode(): null)
                                                .topCategoryName(category.getTopCategoryName() != null ? category.getTopCategoryName(): null)
                                                .middleCategoryName(category.getMiddleCategoryName() != null ? category.getMiddleCategoryName(): null)
                                                .bottomCategoryName(category.getBottomCategoryName() != null ? category.getBottomCategoryName(): null)
                                                .build()
                                ).toList() : null
                )
                    .mentoringHashtags( // 멘토링 해시태그
                            editDto.getHashTagList() != null ? editDto.getHashTagList().stream()
                                    .map(
                                            hashtag -> MentoringHashtagDomain.builder()
                                                    .mentoringUuid(mentoringResponseOutDto.getUuid())
                                                    .hashtagId(hashtag.getHashtagId())
                                                    .hashtagName(hashtag.getHashtagName())
                                                    .build()
                                    ).toList() : null
                    )
                .build();
    }
}
