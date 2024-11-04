package com.mentoring.demo.mentoring.adaptor.out.kafka;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddAfterOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, MentoringAddAfterOutDto> kafkaAddMentoringTemplate;
    private final KafkaTemplate<String, MentoringEditRequestDto> kafkaEditMentoringTemplate;

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
    public void sendUpdateMentoring(String topic, MentoringEditRequestDto dto) {
        log.info("send update dto :"+dto);
        try {
            kafkaEditMentoringTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("update mentoring event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }
}