package com.mentoring.demo.mentoring.domain.model;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringSessionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddAfterOutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MentoringSessionDomain {

    private String uuid;

    private String mentoringId;
    private String mentoringUuid;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime deadlineDate;

    private Integer price;
    private Integer minHeadCount;
    private Integer maxHeadCount;

    private Boolean isClosed;
    private Boolean isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static List<MentoringSessionDomain> createMentoringSession(
            MentoringAddAfterOutDto mentoringAddAfterOutDto, List<MentoringSessionDto> sessionList) {
        return sessionList.stream()
                .map(timeDto -> MentoringSessionDomain.builder()
                        .uuid(UUID.randomUUID().toString()) // 멘토링 세션 UUID 생성

                        .mentoringId(mentoringAddAfterOutDto.getMentoringId())
                        .mentoringUuid(mentoringAddAfterOutDto.getMentoringUuid())

                        .startDate(timeDto.getStartDate())
                        .endDate(timeDto.getEndDate())
                        .startTime(timeDto.getStartTime())
                        .endTime(timeDto.getEndTime())
                        .deadlineDate((timeDto.getDeadline_datetime()))
                        .price(timeDto.getPrice())
                        .minHeadCount(timeDto.getMinHeadCount())
                        .maxHeadCount(timeDto.getMaxHeadCount())
                        .isClosed(false)
                        .isDeleted(false)
                        .build())
                .collect(Collectors.toList());
    }
}
