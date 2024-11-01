package com.mentoring.demo.mentoring.application.port.out.dto;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringSessionDto;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class MentoringAddTransactionDto {
    private String mentoringUuid;
    private String name;
    private String detail;
    private String mentorUuid;
    private String thumbnailUrl;
    private Boolean isReusable;

    private Boolean isDeleted;

    @Setter
    private List<MentoringSessionTransactionDto> sessionList;

    public MentoringEntity toEntity(){
        return MentoringEntity.builder()
                .mentoringUuid(this.mentoringUuid)
                .name(this.name)
                .detail(this.detail)
                .mentorUuid(this.mentorUuid)
                .thumbnailUrl(this.thumbnailUrl)
                .isReusable(this.isReusable)
                .isDeleted(this.isDeleted)
                .build();
    }

    public List<MentoringSessionEntity> toSessionEntity(
                                                    List<MentoringSessionTransactionDto> sessionList,
                                                    MentoringEntity mentoringEntity)
    {
        // stream 으로 변환
        return sessionList.stream()
                .map(session -> MentoringSessionEntity.builder()
                        .uuid(session.getUuid())
                        .mentoringEntity(mentoringEntity)
                        .startDate(session.getStartDate())
                        .endDate(session.getEndDate())
                        .startTime(session.getStartTime())
                        .endTime(session.getEndTime())
                        .deadlineDatetime(session.getDeadlineDatetime())
                        .minHeadCount(session.getMinHeadCount())
                        .maxHeadCount(session.getMaxHeadCount())
                        .price(session.getPrice())
                        .isClosed(session.getIsClosed())
                        .isDeleted(session.getIsDeleted())
                        .build())
                        .toList();
    }

    @Builder
    public MentoringAddTransactionDto(String mentoringUuid, String name, String detail, String mentorUuid,
                                   String thumbnailUrl, Boolean isReusable, Boolean isDeleted) {
        this.mentoringUuid = mentoringUuid;
        this.name = name;
        this.detail = detail;
        this.mentorUuid = mentorUuid;
        this.thumbnailUrl = thumbnailUrl;
        this.isReusable = isReusable;
        this.isDeleted = isDeleted;
    }

    @Builder
    public MentoringAddTransactionDto(List<MentoringSessionTransactionDto> sessionList) {
        this.sessionList = sessionList;
    }
}
