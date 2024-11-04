package com.mentoring.demo.mentoring.adaptor.out.kafka.persistence;

import com.mentoring.demo.mentoring.application.port.in.dto.MentoringEditRequestDto;
import com.mentoring.demo.mentoring.application.port.out.SendMessageOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringAddAfterOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Log4j2
@Component("SendMessageKafkaAdapter")
public class SendMessageKafkaAdapter implements SendMessageOutPort {

    private final KafkaTemplate<String, MentoringAddAfterOutDto> kafkaAddMentoringTemplate;
    private final KafkaTemplate<String, MentoringEditRequestDto> kafkaEditMentoringTemplate;

    @Override
    public void sendCreateMentoringMessage(String topic, MentoringAddAfterOutDto dto) {
        try {
            log.info("sendCreateMentoringMessage 성공 : " + dto);
            log.info("getMentoringSessionAddAfterOutDtoList 성공 : " + dto.getMentoringSessionAddAfterOutDtoList());
            kafkaAddMentoringTemplate.send(topic, dto);
        }
        catch (Exception e) {
            log.info("create mentoring event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }
}
