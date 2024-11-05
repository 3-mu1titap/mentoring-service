package com.mentoring.demo.mentoring.application.port.out.dto;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringCategoryEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringCategoryDto;
import lombok.*;

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
    private String detail;
    private String mentorUuid;
    private Boolean isReusable;
    private Boolean isDeleted;
    private String thumbnailUrl;

    @Setter
    private List<MentoringCategoryAfterOutDto> categoryList;


    public static List<MentoringCategoryEntity> toMentoringCategoryEntity(
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

}
