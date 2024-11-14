package com.mentoring.demo.mentoring.adaptor.in.web.mapper;

import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.MentoringAddRequestVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.in.MentoringEditRequestVo;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringCategoryDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.MentoringSessionDto;
import org.springframework.stereotype.Component;

@Component
public class MentoringVoMapper {
    public static MentoringAddRequestDto from(MentoringAddRequestVo vo) {
        return MentoringAddRequestDto.builder()
                .name(vo.getName())
                .description(vo.getDescription())
                .detail(vo.getDetail())
                .mentorUuid(vo.getMentorUuid())
                .thumbnailUrl(vo.getThumbnailUrl())
                .isReusable(vo.getIsReusable())
                .sessionList(
                        vo.getSessionList().stream()
                                .map(timeVo -> MentoringSessionDto.builder()
                                        .startDate(timeVo.getStartDate())
                                        .endDate(timeVo.getEndDate())
                                        .startTime(timeVo.getStartTime())
                                        .endTime(timeVo.getEndTime())
                                        .deadlineDate(timeVo.getDeadlineDate())
                                        .minHeadCount(timeVo.getMinHeadCount())
                                        .maxHeadCount(timeVo.getMaxHeadCount())
                                        .price(timeVo.getPrice())
                                        .build()
                                )
                                .toList()
                )
                .categoryList(
                        vo.getCategoryList().stream()
                                .map(categoryVo ->
                                            MentoringCategoryDto.builder()
                                                .topCategoryCode(categoryVo.getTopCategoryCode())
                                                .middleCategoryCode(categoryVo.getMiddleCategoryCode())
                                                .bottomCategoryCode(categoryVo.getBottomCategoryCode())
                                                .topCategoryName(categoryVo.getTopCategoryName())
                                                .middleCategoryName(categoryVo.getMiddleCategoryName())
                                                .bottomCategoryName(categoryVo.getBottomCategoryName())
                                                .build()
                                )
                                .toList()
                )
                .build();

    }

    public static MentoringEditRequestDto from(MentoringEditRequestVo vo) {
        return MentoringEditRequestDto.builder()
                .uuid(vo.getUuid())
                .name(vo.getName())
                .description(vo.getDescription())
                .detail(vo.getDetail())
                .isReusable(vo.getIsReusable())
                .thumbnailUrl(vo.getThumbnailUrl())
                .categoryList(
                        vo.getCategoryList().stream()
                                .map(categoryVo ->
                                        MentoringCategoryDto.builder()
                                                .topCategoryCode(categoryVo.getTopCategoryCode())
                                                .middleCategoryCode(categoryVo.getMiddleCategoryCode())
                                                .bottomCategoryCode(categoryVo.getBottomCategoryCode())
                                                .topCategoryName(categoryVo.getTopCategoryName())
                                                .middleCategoryName(categoryVo.getMiddleCategoryName())
                                                .bottomCategoryName(categoryVo.getBottomCategoryName())
                                                .build()
                                )
                                .toList()
                )
                .build();
    }
}
