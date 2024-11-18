package com.mentoring.demo.mentoring.adaptor.out.kafka;

import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringEditRequestOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionCreatedAfterOutDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    // 멘토링 생성 DTO
    @Bean
    public ProducerFactory<String, MentoringAddAfterOutDto> mentoringProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092,localhost:49092");
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-1:19092,kafka-2:19092,kafka-3:19092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, MentoringAddAfterOutDto> kafkaAddMentoringTemplate() {
        return new KafkaTemplate<>(mentoringProducerFactory());
    }

    // 멘토링 수정 DTO
    @Bean
    public ProducerFactory<String, MentoringEditRequestOutDto> mentoringEditProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092,localhost:49092");
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-1:19092,kafka-2:19092,kafka-3:19092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, MentoringEditRequestOutDto> kafkaEditMentoringTemplate() {
        return new KafkaTemplate<>(mentoringEditProducerFactory());
    }

    // 세션 추가
    @Bean
    public ProducerFactory<String, SessionCreatedAfterOutDto> sessionAddProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092,localhost:49092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, SessionCreatedAfterOutDto> kafkaSessionAddTemplate() {
        return new KafkaTemplate<>(sessionAddProducerFactory());
    }
}
