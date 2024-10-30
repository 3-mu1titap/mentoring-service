package com.mentoring.demo.mentoring.adaptor.in.web.mapper;

import com.mentoring.demo.mentoring.adaptor.in.web.vo.MentoringAddRequestVo;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.MentoringEditRequestVo;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringSessionDto;
import org.springframework.stereotype.Component;

@Component
public class MentoringVoMapper {
    public static MentoringAddRequestDto toCreateMentoringDto(MentoringAddRequestVo vo) {
        return MentoringAddRequestDto.builder()
                .name(vo.getName())
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
                                        .minHeadCount(timeVo.getMinHeadCount())
                                        .maxHeadCount(timeVo.getMaxHeadCount())
                                        .price(timeVo.getPrice())
                                        .build()
                                )
                                .toList()
                )
                .build();

    }

    public static MentoringEditRequestDto toUpdateMentoringDto(MentoringEditRequestVo vo) {
        return MentoringEditRequestDto.builder()
                .uuid(vo.getUuid())
                .name(vo.getName())
                .detail(vo.getDetail())
                .isReusable(vo.getIsReusable())
                .thumbnailUrl(vo.getThumbnailUrl())
                .build();
    }
}
