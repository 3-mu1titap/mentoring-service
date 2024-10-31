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
    private final ObjectMapper mapper;

//    public void sendOrderMessageObject(OrderDto orderDto) {
//        try {
//            orderKafkaTemplate.send(ORDER_OBJECT_TOPIC, orderDto);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    /**
     * 멘토링 생성 이벤트 발생
     */
//    public void sendCreateMentoring(String topic, MentoringAddAfterDto dto) {
//        log.info("send dto :"+dto);
//        String jsonInString = "";
//        try {
//            // Dto를 JSON 형식으로 변환
//            jsonInString = mapper.writeValueAsString(dto);
//            kafkaTemplate.send(topic, jsonInString);
//            log.info("멘토링 생성 이벤트 jsonInString : " + jsonInString);
//        } catch (JsonProcessingException ex) {
//            log.error("Failed to convert MentoringAddRequestDto to JSON", ex);
//        }
//
//    }
    public void sendCreateMentoring(String topic, MentoringAddAfterDto dto) {
        log.info("send dto :"+dto);

        kafkaAddMentoringTemplate.send(topic, dto);

    }

    /**
     * 멘토링 수정 이벤트 발생
     */
//    public MentoringEditRequestDto sendUpdateMentoring(String topic, MentoringEditRequestDto dto) {
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonInString = "";
//        try {
//            jsonInString = mapper.writeValueAsString(dto);
//        } catch(JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//
//        kafkaTemplate.send(topic, jsonInString);
//
//        return dto;
//    }
}