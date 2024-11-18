package com.mentoring.demo.mentoring.domain.model;

import com.mentoring.demo.mentoring.application.port.in.dto.in.AddMentoringSessionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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

}
