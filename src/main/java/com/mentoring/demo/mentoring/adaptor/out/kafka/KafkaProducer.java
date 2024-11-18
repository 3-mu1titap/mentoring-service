package com.mentoring.demo.mentoring.adaptor.out.kafka;

import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringEditRequestOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionCreatedAfterOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, MentoringAddAfterOutDto> kafkaAddMentoringTemplate;
    private final KafkaTemplate<String, MentoringEditRequestOutDto> kafkaEditMentoringTemplate;
    private final KafkaTemplate<String, SessionCreatedAfterOutDto> kafkaSessionAddTemplate;

    /**
     * 멘토링 생성 이벤트 발생
     */
    public void sendCreateMentoring(String topic, MentoringAddAfterOutDto dto) {
        try {
            kafkaAddMentoringTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("create mentoring event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 멘토링 수정 이벤트 발생
     */
    public void sendUpdateMentoring(String topic, MentoringEditRequestOutDto dto) {
        log.info("send update dto :"+dto);
        try {
            kafkaEditMentoringTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("update mentoring event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 세션 추가 이벤트 발생
     */
    public void sendUpdateMentoring(String topic, SessionCreatedAfterOutDto dto) {
        log.info("send add session dto :"+dto);
        try {
            kafkaSessionAddTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("add session event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }
}