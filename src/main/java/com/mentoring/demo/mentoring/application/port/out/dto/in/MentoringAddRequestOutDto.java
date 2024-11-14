package com.mentoring.demo.mentoring.application.port.out.dto.in;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringCategoryEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class  MentoringAddRequestOutDto {
    private String mentoringUuid;
    private String name;
    private String description;
    private String detail;
    private String mentorUuid;
    private String thumbnailUrl;
    private Boolean isReusable;

    private Boolean isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Setter
    private List<MentoringSessionOutDto> sessionList;
    @Setter
    private List<MentoringCategoryOutDto> categoryList;

    public MentoringEntity toEntity(){
        return MentoringEntity.builder()
                .mentoringUuid(this.mentoringUuid)
                .name(this.name)
                .description(this.description)
                .detail(this.detail)
                .mentorUuid(this.mentorUuid)
                .thumbnailUrl(this.thumbnailUrl)
                .isReusable(this.isReusable)
                .isDeleted(this.isDeleted)
                .build();
    }

    public List<MentoringSessionEntity> toSessionEntity(
                                                    List<MentoringSessionOutDto> sessionList,
                                                    MentoringEntity mentoringEntity)
    {
        // stream 으로 변환
        return sessionList.stream()
                .map(session -> MentoringSessionEntity.builder()
                        .uuid(session.getUuid())
                        .mentoringEntity(mentoringEntity)
                        .startDate(session.getStartDate())
                        .endDate(session.getEndDate())
                        .startTime(session.getStartTime())
                        .endTime(session.getEndTime())
                        .deadlineDate(session.getDeadlineDate())
                        .minHeadCount(session.getMinHeadCount())
                        .maxHeadCount(session.getMaxHeadCount())
                        .price(session.getPrice())
                        .isClosed(session.getIsClosed())
                        .isDeleted(session.getIsDeleted())
                        .build())
                        .toList();
    }

    public static List<MentoringCategoryEntity> toMentoringCategoryEntity(
            MentoringAddAfterOutDto afterOutDto, List<MentoringCategoryOutDto> categoryOutList
    )
    {
        MentoringEntity mentoringEntity = MentoringEntity.builder()
                .id(Long.valueOf(afterOutDto.getMentoringId()))
                .mentoringUuid(afterOutDto.getMentoringUuid())
                .name(afterOutDto.getName())
                .detail(afterOutDto.getDetail())
                .mentorUuid(afterOutDto.getMentorUuid())
                .thumbnailUrl(afterOutDto.getThumbnailUrl())
                .isReusable(afterOutDto.getIsReusable())
                .isDeleted(afterOutDto.getIsDeleted())
                .build();

        // stream 으로 변환
        return categoryOutList.stream()
                .map(category -> MentoringCategoryEntity.builder()
                        .mentoringUuid(afterOutDto.getMentoringUuid())
                        .topCategoryCode(category.getTopCategoryCode())
                        .middleCategoryCode(category.getMiddleCategoryCode())
                        .bottomCategoryCode(category.getBottomCategoryCode())
                        .mentoringEntity(mentoringEntity)
                        .build())
                        .toList();
    }

}
