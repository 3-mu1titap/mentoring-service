package com.mentoring.demo.mentoring.application.port.out.dto.out;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AfterHashtag {
    private String hashtagId;
    private String hashtagName;
}
