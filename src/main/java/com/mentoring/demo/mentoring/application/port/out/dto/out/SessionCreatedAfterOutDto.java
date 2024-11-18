package com.mentoring.demo.mentoring.application.port.out.dto.out;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "sessionAddAfterOutDtos")
public class SessionCreatedAfterOutDto {
    private String mentoringId;
    private String mentoringUuid;

    private String mentorUuid;
    private String mentoringName;
    List<SessionAddAfterOutDto> sessionAddAfterOutDtos;
}
