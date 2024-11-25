package com.mentoring.demo.mentoring.domain.model;

import com.mentoring.demo.mentoring.application.port.in.dto.in.AddMentoringSessionDto;
import com.mentoring.demo.mentoring.domain.vo.TimeRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MentoringSessionDomain {

    private String uuid; // 멘토링 세션 uuid

    private String mentoringId;
    private String mentoringUuid;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate deadlineDate;

    private Integer price;
    private Integer minHeadCount;
    private Integer maxHeadCount;

    private Boolean isClosed;
    private Boolean isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static List<MentoringSessionDomain> createSession(String mentoringUuid,
                                                             List<AddMentoringSessionDto> addmentoringSessionDtoList
    )
    {
        List<MentoringSessionDomain> mentoringSessionDomainList = new ArrayList<>();
        for (AddMentoringSessionDto addMentoringSessionDto : addmentoringSessionDtoList) {
            mentoringSessionDomainList.add(MentoringSessionDomain.builder()
                    .uuid(UUID.randomUUID().toString()) // 세션 uuid 생성
                    .mentoringUuid(mentoringUuid)
                    .startDate(addMentoringSessionDto.getStartDate())
                    .endDate(addMentoringSessionDto.getEndDate())
                    .startTime(addMentoringSessionDto.getStartTime())
                    .endTime(addMentoringSessionDto.getEndTime())
                    .deadlineDate(addMentoringSessionDto.getDeadlineDate())
                    .price(addMentoringSessionDto.getPrice())
                    .minHeadCount(addMentoringSessionDto.getMinHeadCount())
                    .maxHeadCount(addMentoringSessionDto.getMaxHeadCount())
                    .isClosed(false)
                    .isDeleted(false)
                    .build());
        }
        return mentoringSessionDomainList;
    }

    public static List<MentoringSessionDomain> createBatchSession(String mentoringUuid,
                                                                  Map<LocalDate, List<TimeRange>> sessionListByDate) {
        List<MentoringSessionDomain> result = new ArrayList<>();
        for (LocalDate date : sessionListByDate.keySet()) {
            for (TimeRange timeRange : sessionListByDate.get(date)) {
                result.add(MentoringSessionDomain.builder()
                        .uuid(UUID.randomUUID().toString()) // 세션 uuid 생성
                        .mentoringUuid(mentoringUuid)
                        .startDate(date)
                        .endDate(timeRange.getIsNextDay() ? date.plusDays(1) : date)
                        .startTime(timeRange.getStartTime())
                        .endTime(timeRange.getEndTime())
                        .deadlineDate(date.minusDays(1))
                        .price(timeRange.getPrice())
                        .minHeadCount(timeRange.getMinHeadCount())
                        .maxHeadCount(timeRange.getMaxHeadCount())
                        .isClosed(false)
                        .isDeleted(false)
                        .build());
            }
        }

        return result;

    }

}
