package com.mentoring.demo.mentoring.application.scheduler;

import com.mentoring.demo.mentoring.application.port.in.SendMessageUseCase;
import com.mentoring.demo.mentoring.application.port.in.SessionInquiryUseCase;
import com.mentoring.demo.mentoring.application.port.out.SendMessageOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.out.DeadlinePastSessionResponseOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class SessionScheduler {
    private final SessionInquiryUseCase sessionInquiryUseCase;
    private final SendMessageOutPort sendMessageOutPort;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul") // 매일 00시 마다 실행
    //@Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul") // 1분마다 실행
    public void processSessionsPastDeadline() {
        // 오늘 예약마감일이 끝난 세션 조회 휴 "deadline-past-session" 이벤트 메시지 발행
        sessionInquiryUseCase.getPastDeadlineSessions()
                .forEach(session -> {
                    sendMessageOutPort.sendDeadlinePastSessionMessage("deadline-past-session", session);
                }
        );
    }


}
