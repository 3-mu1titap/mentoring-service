package com.mentoring.demo.mentoring.application.port.out.dto.in;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentoringSessionOutDto {

    private String uuid; // 세션 uuid

    private String mentoringId;
    private String mentoringUuid;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate deadlineDate;

    private Integer minHeadCount;

    private Integer maxHeadCount;

    private Integer price;

    private Boolean isClosed;
    private Boolean isDeleted;

    public static List<MentoringSessionEntity> toEntities(
            MentoringAddAfterOutDto afterOutDto, MentoringAddRequestOutDto outDto)
    {
        MentoringEntity mentoringEntity = MentoringEntity.builder()
                .id(Long.valueOf(afterOutDto.getMentoringId()))
                .mentoringUuid(outDto.getMentoringUuid())
                .name(outDto.getName())
                .detail(outDto.getDetail())
                .mentorUuid(outDto.getMentorUuid())
                .thumbnailUrl(outDto.getThumbnailUrl())
                .isReusable(outDto.getIsReusable())
                .isDeleted(outDto.getIsDeleted())
                .build();

        return outDto.getSessionList().stream()
                .map(
                    mentoringSessionTransactionDto
                            -> MentoringSessionEntity.builder()
                                .uuid(mentoringSessionTransactionDto.getUuid())
                                .mentoringEntity(mentoringEntity)
                                .startDate(mentoringSessionTransactionDto.getStartDate())
                                .endDate(mentoringSessionTransactionDto.getEndDate())
                                .startTime(mentoringSessionTransactionDto.getStartTime())
                                .endTime(mentoringSessionTransactionDto.getEndTime())
                                .deadlineDate(mentoringSessionTransactionDto.getDeadlineDate())
                                .minHeadCount(mentoringSessionTransactionDto.getMinHeadCount())
                                .maxHeadCount(mentoringSessionTransactionDto.getMaxHeadCount())
                                .price(mentoringSessionTransactionDto.getPrice())
                                .isClosed(mentoringSessionTransactionDto.getIsClosed())
                                .isDeleted(mentoringSessionTransactionDto.getIsDeleted())
                                .build()
                ).toList();
    }
    //

    private List<MentoringSessionEntity> of (List<MentoringSessionOutDto> sessionOutDtos, MentoringEntity mentoringEntity) {
        return sessionOutDtos.stream()
                .map(dto -> MentoringSessionEntity.builder()
                        .uuid(dto.getUuid())
                        .mentoringEntity(mentoringEntity)
                        .startDate(dto.getStartDate())
                        .endDate(dto.getEndDate())
                        .startTime(dto.getStartTime())
                        .endTime(dto.getEndTime())
                        .deadlineDate(dto.getDeadlineDate())
                        .minHeadCount(dto.getMinHeadCount())
                        .maxHeadCount(dto.getMaxHeadCount())
                        .price(dto.getPrice())
                        .isClosed(dto.getIsClosed())
                        .isDeleted(dto.getIsDeleted())
                        .build())
                .collect(Collectors.toList());
    }


}
