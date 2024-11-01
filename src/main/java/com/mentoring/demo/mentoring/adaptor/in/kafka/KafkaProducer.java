package com.mentoring.demo.mentoring.adaptor.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddAfterDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class KafkaProducer {
    //private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, MentoringAddAfterDto> kafkaAddMentoringTemplate;
    private final KafkaTemplate<String, MentoringEditRequestDto> kafkaEditMentoringTemplate;
    private final ObjectMapper mapper;

    /**
     * 멘토링 생성 이벤트 발생
     */
    public void sendCreateMentoring(String topic, MentoringAddAfterDto dto) {
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