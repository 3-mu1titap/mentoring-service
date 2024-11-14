package com.mentoring.demo.mentoring.application.port.in;

import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionResponseOutDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MentoringSessionUseCaseTest {
    @Autowired MentoringSessionUseCase mentoringSessionUseCase;

    @Test
    void getSessionByUuid() {
        String uuid = "ac419217-cb98-4334-8b78-8126aa0e57aa";
        SessionResponseOutDto outDto = mentoringSessionUseCase.getSessionOutDtoByUuid(uuid);
        log.info("outDto : "+outDto);
    }

}