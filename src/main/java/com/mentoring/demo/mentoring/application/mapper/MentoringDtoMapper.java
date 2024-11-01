package com.mentoring.demo.mentoring.application.mapper;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddAfterDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringSessionAddAfterDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddTransactionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringEditTransactionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringSessionTransactionDto;
import com.mentoring.demo.mentoring.domain.model.MentoringDomain;
import com.mentoring.demo.mentoring.domain.model.MentoringSessionDomain;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MentoringDtoMapper {

    // MentoringDomain -> MentoringTransactionDto 변환
    public static MentoringAddTransactionDto toMentoringTransactionDto(MentoringDomain domain) {
        return MentoringAddTransactionDto.builder()
                .mentoringUuid(domain.getUuid())
                .name(domain.getName())
                .detail(domain.getDetail())
                .mentorUuid(domain.getMentorUuid())
                .thumbnailUrl(domain.getThumbnailUrl())
                .isReusable(domain.getIsReusable())
                .isDeleted(domain.getIsDeleted())
                //.sessionList()
                .build();
    }

    // MentoringSessionDomain -> MentoringSessionTransactionDto 변환
    public static List<MentoringSessionTransactionDto> toSessionTransactionDto(
                                                                    List<MentoringSessionDomain> sessionDomain) {
        return sessionDomain.stream()
                .map(session -> MentoringSessionTransactionDto.builder()
                        .uuid(session.getUuid())

                        .mentoringId(session.getMentoringId())
                        .mentoringUuid(session.getMentoringUuid())

                        .startDate(session.getStartDate())
                        .endDate(session.getEndDate())
                        .startTime(session.getStartTime())
                        .endTime(session.getEndTime())
                        .deadlineDatetime(session.getDeadlineDate())
                        .minHeadCount(session.getMinHeadCount())
                        .maxHeadCount(session.getMaxHeadCount())
                        .price(session.getPrice())
                        .isClosed(session.getIsClosed())
                        .isDeleted(session.getIsDeleted())
                        .build())
                .toList();
    }

    public  static MentoringEditTransactionDto toMentoringEditTransactionDto(MentoringDomain domain) {
        return MentoringEditTransactionDto.builder()
                .id(domain.getId())
                .uuid(domain.getUuid())
                .name(domain.getName())
                .detail(domain.getDetail())
                .mentorUuid(domain.getMentorUuid())
                .thumbnailUrl(domain.getThumbnailUrl())
                .isReusable(domain.getIsReusable())
                .isDeleted(domain.getIsDeleted())
                .build();
    }

    public static MentoringAddAfterDto toMentoringAddAfterDto(MentoringAddAfterOutDto outDto) {
        return MentoringAddAfterDto.builder()
                .mentoringId(outDto.getMentoringId())
                .mentoringUuid(outDto.getMentoringUuid())
                .name(outDto.getName())
                .detail(outDto.getDetail())
                .mentorUuid(outDto.getMentorUuid())
                .thumbnailUrl(outDto.getThumbnailUrl())
                .isReusable(outDto.getIsReusable())
                .isDeleted(outDto.getIsDeleted())
//                .mentoringSessionAddAfterDtoList(
//                        outDto.getMentoringSessionAddAfterOutDtoList().stream()
//                                .map(session -> MentoringSessionAddAfterDto.builder()
//                                                    .sessionId(session.getSessionId())
//                                                    .sessionUuid(session.getSessionUuid())
//                                                    // 멘토링 생성 후 id 할당
//                                                    .mentoringId(session.getMentoringId())
//                                                    .mentoringUuid(session.getMentoringUuid())
//                                                    .startDate(session.getStartDate())
//                                                    .endDate(session.getEndDate())
//                                                    .startTime(session.getStartTime())
//                                                    .endTime(session.getEndTime())
//                                                    .deadlineDatetime(session.getDeadlineDatetime())
//                                                    .minHeadCount(session.getMinHeadCount())
//                                                    .maxHeadCount(session.getMaxHeadCount())
//                                                    .price(session.getPrice())
//                                                    .isClosed(session.getIsClosed())
//                                                    .build())
//                                .toList()
//                )
                .build();
    }

}
