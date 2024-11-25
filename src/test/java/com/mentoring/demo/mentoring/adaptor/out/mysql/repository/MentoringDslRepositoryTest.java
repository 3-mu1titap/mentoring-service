package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.application.port.out.SendMessageOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.out.DeadlinePastSessionResponseOutDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MentoringDslRepositoryTest {
    @Autowired MentoringSessionDslRepository mentoringSessionDslRepository;
    @Autowired SendMessageOutPort sendMessageOutPort;
    @Test
    void findPastDeadlineSessions(){
        LocalDate localDate = LocalDate.of(2024, 12, 2);
        List<DeadlinePastSessionResponseOutDto> pastDeadlineSessions = mentoringSessionDslRepository.findPastDeadlineSessions(localDate);
        pastDeadlineSessions.forEach(log::info);

//        pastDeadlineSessions.forEach(session ->
//                sendMessageOutPort.sendDeadlinePastSessionMessage("deadline-past-session", session)
//        );

        //pastDeadlineSessions.forEach(log::info);
    }
}