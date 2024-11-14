package com.mentoring.demo.mentoring.application.port.out.dto.in;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringCategoryEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.domain.model.MentoringDomain;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "categoryList")
public class MentoringEditRequestOutDto {
    private String id;
    private String uuid;
    private String name;
    private String description;
    private String detail;
    private String mentorUuid;
    private Boolean isReusable;
    private Boolean isDeleted;
    private String thumbnailUrl;
    private LocalDateTime updatedAt;

    @Setter
    private List<MentoringCategoryAfterOutDto> categoryList;


    public static List<MentoringCategoryEntity> of(
            MentoringEntity mentoringEntity, List<MentoringCategoryAfterOutDto> categoryOutList
    )
    {
        // stream 으로 변환
        return categoryOutList.stream()
                .map(category -> MentoringCategoryEntity.builder()
                        .mentoringUuid(category.getMentoringUuid())
                        .topCategoryCode(category.getTopCategoryCode())
                        .middleCategoryCode(category.getMiddleCategoryCode())
                        .bottomCategoryCode(category.getBottomCategoryCode())
                        .mentoringEntity(mentoringEntity)
                        .build())
                .toList();
    }
    public static MentoringEditRequestOutDto from(MentoringDomain domain) {
        return MentoringEditRequestOutDto.builder()
                .id(domain.getId())
                .uuid(domain.getUuid())
                .name(domain.getName())
                .description(domain.getDescription())
                .detail(domain.getDetail())
                .mentorUuid(domain.getMentorUuid())
                .thumbnailUrl(domain.getThumbnailUrl())
                .isReusable(domain.getIsReusable())
                .isDeleted(domain.getIsDeleted())
                .categoryList(
                        domain.getMentoringCategories()
                                .stream()
                                .map(categoryDomain -> MentoringCategoryAfterOutDto.builder()
                                        .mentoringUuid(categoryDomain.getMentoringUuid())
                                        .topCategoryCode(categoryDomain.getTopCategoryCode())
                                        .middleCategoryCode(categoryDomain.getMiddleCategoryCode())
                                        .bottomCategoryCode(categoryDomain.getBottomCategoryCode())
                                        .build())
                                .toList()
                )
                .build();
    }

}
