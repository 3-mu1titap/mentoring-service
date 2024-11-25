package com.mentoring.demo.mentoring.application.service;

import com.mentoring.demo.mentoring.application.port.in.BatchCreationOfSessionUseCase;
import com.mentoring.demo.mentoring.application.port.in.dto.in.BatchCreationOfSessionDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.TimeRangeDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.TimeSlotDto;
import com.mentoring.demo.mentoring.domain.vo.SessionBatchCreationManagementVo;
import com.mentoring.demo.mentoring.domain.vo.TimeRange;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BatchCreationOfSessionServiceTest {
    @Autowired
    BatchCreationOfSessionUseCase batchCreationOfSessionUseCase;
    @Test
    @Rollback
    @Transactional
    void batchCreationOfSession() {
        BatchCreationOfSessionDto batchCreationOfSessionDto = BatchCreationOfSessionDto.builder()
                .mentoringUuid("5f3a302d-f07f-495c-b090-26c11a9e3d8d")
                .deadLineDate(LocalDate.now().plusDays(14))
                .timeSlotDtoList(
                        List.of(
                                TimeSlotDto.builder()
                                        .dayOfWeek(DayOfWeek.MONDAY)
                                        .timeRanges(List.of(
                                                TimeRangeDto.builder()
                                                        .startTime(LocalTime.of(9, 0))
                                                        .endTime(LocalTime.of(10, 0))
                                                        .minHeadCount(1)
                                                        .maxHeadCount(2)
                                                        .price(1000).build(),
                                                TimeRangeDto.builder()
                                                        .startTime(LocalTime.of(11, 0))
                                                        .endTime(LocalTime.of(11, 30))
                                                        .minHeadCount(1)
                                                        .maxHeadCount(2)
                                                        .price(1000).build(),
                                                TimeRangeDto.builder()
                                                        .startTime(LocalTime.of(23, 30))
                                                        .endTime(LocalTime.of(1, 0))
                                                        .minHeadCount(1)
                                                        .maxHeadCount(2)
                                                        .price(1000).build()
                                        ))
                                        .build(),

                                TimeSlotDto.builder()
                                        .dayOfWeek(DayOfWeek.TUESDAY)
                                        .timeRanges(List.of(
                                                TimeRangeDto.builder()
                                                        .startTime(LocalTime.of(12, 50))
                                                        .endTime(LocalTime.of(13, 10))
                                                        .minHeadCount(1)
                                                        .maxHeadCount(2)
                                                        .price(1000).build(),
                                                TimeRangeDto.builder()
                                                        .startTime(LocalTime.of(16, 0))
                                                        .endTime(LocalTime.of(16, 40))
                                                        .minHeadCount(1)
                                                        .maxHeadCount(2)
                                                        .price(1000).build(),
                                                TimeRangeDto.builder()
                                                        .startTime(LocalTime.of(23, 0))
                                                        .endTime(LocalTime.of(2, 0))
                                                        .minHeadCount(1)
                                                        .maxHeadCount(2)
                                                        .price(1000).build()
                                        ))
                                        .build(),

                                TimeSlotDto.builder()
                                        .dayOfWeek(DayOfWeek.THURSDAY)
                                        .timeRanges(List.of(
                                                TimeRangeDto.builder()
                                                        .startTime(LocalTime.of(13, 0))
                                                        .endTime(LocalTime.of(14, 0))
                                                        .minHeadCount(1)
                                                        .maxHeadCount(2)
                                                        .price(1000).build()
                                        ))
                                        .build(),

                                TimeSlotDto.builder()
                                        .dayOfWeek(DayOfWeek.FRIDAY)
                                        .timeRanges(List.of(
                                                TimeRangeDto.builder()
                                                        .startTime(LocalTime.of(13, 0))
                                                        .endTime(LocalTime.of(14, 0))
                                                        .minHeadCount(1)
                                                        .maxHeadCount(2)
                                                        .price(1000).build()
                                        ))
                                        .build()
                        )
                )
                .build();
        batchCreationOfSessionUseCase.batchCreationOfSession(batchCreationOfSessionDto);
    }

}