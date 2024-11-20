package com.mentoring.demo.mentoring.adaptor.out.kafka.persistence;

import com.mentoring.demo.mentoring.application.port.out.SendMessageOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.out.DeadlinePastSessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringEditRequestOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionCreatedAfterOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Log4j2
@Component("SendMessageKafkaAdapter")
public class SendMessageKafkaAdapter implements SendMessageOutPort {

    private final KafkaTemplate<String, MentoringAddAfterOutDto> kafkaAddMentoringTemplate;
    private final KafkaTemplate<String, MentoringEditRequestOutDto> kafkaEditMentoringTemplate;
    private final KafkaTemplate<String, SessionCreatedAfterOutDto> kafkaSessionAddTemplate;
    private final KafkaTemplate<String, DeadlinePastSessionResponseOutDto> kafkaDeadlinePastSessionTemplate;

    @Override
    public void sendCreateMentoringMessage(String topic, MentoringAddAfterOutDto dto) {
        try {
            kafkaAddMentoringTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("create mentoring event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendUpdateMentoringMessage(String topic, MentoringEditRequestOutDto dto) {
        try {
            log.info("sendUpdateMentoringMessage 성공 : " + dto);
            log.info("sendUpdateMentoringMessage 카테고리 리스트 : " + dto.getCategoryList());
            kafkaEditMentoringTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("update mentoring event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendAddSessionMessage(String topic, SessionCreatedAfterOutDto dto) {
        try {
            kafkaSessionAddTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("add session event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendDeadlinePastSessionMessage(String topic, DeadlinePastSessionResponseOutDto dto) {
        try {
            kafkaDeadlinePastSessionTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("deadline past session event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }
}
