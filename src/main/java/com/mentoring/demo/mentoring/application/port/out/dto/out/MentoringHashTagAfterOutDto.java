package com.mentoring.demo.mentoring.application.port.out.dto.out;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MentoringHashTagAfterOutDto {
    private String mentoringId;
    private List<AfterHashtag> afterHashtagList;
}
