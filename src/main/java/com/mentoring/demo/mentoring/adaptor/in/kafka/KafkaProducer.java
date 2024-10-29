package com.mentoring.demo.mentoring.adaptor.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentoring.demo.mentoring.adaptor.in.web.vo.MentoringAddRequestVo;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringAddRequestDto;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class KafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 멘토링 생성 이벤트 발생
     */
    public MentoringAddRequestDto sendCreateMentoring(String topic, MentoringAddRequestDto dto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            //orderDto를 제이슨 포맷으로 변경
            jsonInString = mapper.writeValueAsString(dto);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);

        return dto;
    }
}