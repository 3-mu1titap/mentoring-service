package com.mentoring.demo.mentoring.application.port.in.dto.out;

import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeResponseOutDto;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SessionValidationResponseDto {
    private Boolean isPossible;
    private SessionTimeResponseOutDto timeDuplicateResponse;

}
