package com.mentoring.demo.mentoring.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MentoringHashtagDomain {
    private String id;
    private String mentoringUuid;

    private String hashtagId;
    private String hashtagName;

//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}
