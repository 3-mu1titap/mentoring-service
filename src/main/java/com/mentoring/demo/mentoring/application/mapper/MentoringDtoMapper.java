package com.mentoring.demo.mentoring.application.mapper;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddAfterDto;
import com.mentoring.demo.mentoring.application.port.out.dto.*;
import com.mentoring.demo.mentoring.domain.model.MentoringDomain;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MentoringDtoMapper {

    // MentoringDomain -> MentoringTransactionDto 변환
    public static MentoringAddRequestOutDto toMentoringTransactionDto(MentoringDomain domain) {
        return MentoringAddRequestOutDto.builder()
                .mentoringUuid(domain.getUuid())
                .name(domain.getName())
                .detail(domain.getDetail())
                .mentorUuid(domain.getMentorUuid())
                .thumbnailUrl(domain.getThumbnailUrl())
                .isReusable(domain.getIsReusable())
                .isDeleted(domain.getIsDeleted())
                .sessionList(toSessionOutDto(domain)) // 세션 리스트 변환
                .categoryList(mentoringCategoryOutDto(domain)) // 카테고리 리스트 변환
                .build();
    }

    // MentoringSessionDomain -> MentoringSessionTransactionDto 변환
    public static List<MentoringSessionOutDto> toSessionOutDto(MentoringDomain domain) {
        return domain.getMentoringSessions().stream()
                .map(session -> MentoringSessionOutDto.builder()
                        .uuid(session.getUuid())
                        .mentoringId(session.getMentoringId())
                        .mentoringUuid(session.getMentoringUuid())
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
    public static List<MentoringCategoryOutDto> mentoringCategoryOutDto (MentoringDomain domain){
        return domain.getMentoringCategories().stream()
                .map(category -> MentoringCategoryOutDto.builder()
                                    .mentoringUuid(category.getMentoringUuid())
                                    .topCategoryCode(category.getTopCategoryCode())
                                    .middleCategoryCode(category.getMiddleCategoryCode())
                                    .bottomCategoryCode(category.getBottomCategoryCode())
                                    .topCategoryName(category.getTopCategoryName())
                                    .middleCategoryName(category.getMiddleCategoryName())
                                    .bottomCategoryName(category.getBottomCategoryName())
                                    .build())
                .toList();
    }
    public static List<MentoringCategoryAfterOutDto> toMentoringCategoryAfterOutDto (MentoringDomain domain){
        return domain.getMentoringCategories().stream()
                .map(category -> MentoringCategoryAfterOutDto.builder()
                                    .mentoringUuid(category.getMentoringUuid())
                                    .topCategoryCode(category.getTopCategoryCode())
                                    .middleCategoryCode(category.getMiddleCategoryCode())
                                    .bottomCategoryCode(category.getBottomCategoryCode())
                                    .topCategoryName(category.getTopCategoryName())
                                    .middleCategoryName(category.getMiddleCategoryName())
                                    .bottomCategoryName(category.getBottomCategoryName())
                                    .build())
                .toList();
    }

    public  static MentoringEditRequestOutDto toMentoringEditRequestOutDto(MentoringDomain domain) {
        return MentoringEditRequestOutDto.builder()
                .id(domain.getId())
                .uuid(domain.getUuid())
                .name(domain.getName())
                .detail(domain.getDetail())
                .mentorUuid(domain.getMentorUuid())
                .thumbnailUrl(domain.getThumbnailUrl())
                .isReusable(domain.getIsReusable())
                .isDeleted(domain.getIsDeleted())
                .categoryList(toMentoringCategoryAfterOutDto(domain))
                .build();
    }


}
