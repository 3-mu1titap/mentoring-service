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
    void convertSessionListByDateUntilDeadline(){

        List<TimeRangeDto> timeRangeDtos = List.of(
                TimeRangeDto.builder()
                        .startTime(LocalTime.of(9, 0))
                        .endTime(LocalTime.of(10, 0))
                        .minHeadCount(1)
                        .maxHeadCount(2)
                        .price(1000)
                        .dayOfWeekList(List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY))
                        .build(),
                TimeRangeDto.builder()
                        .startTime(LocalTime.of(23, 30))
                        .endTime(LocalTime.of(1, 0))
                        .minHeadCount(1)
                        .maxHeadCount(2)
                        .price(1000)
                        .dayOfWeekList(List.of(DayOfWeek.THURSDAY, DayOfWeek.SUNDAY))
                        .build(),
                TimeRangeDto.builder()
                        .startTime(LocalTime.of(11, 0))
                        .endTime(LocalTime.of(11, 30))
                        .minHeadCount(1)
                        .maxHeadCount(2)
                        .price(1000)
                        .build()
        );

        LocalDate creationStartDate = LocalDate.now();
        LocalDate creationEndDate = LocalDate.now().plusDays(30);

        Map<LocalDate, List<TimeRange>> result = SessionBatchCreationManagementVo.convertSessionListByDateUntilDeadline(timeRangeDtos, creationStartDate, creationEndDate);
    }

    @Test
    @Rollback
    @Transactional
    void batchCreationOfSession() {
        BatchCreationOfSessionDto batchCreationOfSessionDto = BatchCreationOfSessionDto.builder()
                .mentoringUuid("60bd7d3f-a4c3-4861-ab66-e903963669cb")
                .creationStartDate(LocalDate.now().plusDays(1))
                .creationEndDate(LocalDate.now().plusDays(1))
                .timeRanges(
                        List.of(
                                TimeRangeDto.builder()
                                        .startTime(LocalTime.of(9, 0))
                                        .endTime(LocalTime.of(10, 0))
                                        .minHeadCount(1)
                                        .maxHeadCount(2)
                                        .price(1000)
                                        .dayOfWeekList(List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY))
                                        .build(),
                                TimeRangeDto.builder()
                                        .startTime(LocalTime.of(23, 30))
                                        .endTime(LocalTime.of(1, 0))
                                        .minHeadCount(1)
                                        .maxHeadCount(2)
                                        .price(1000)
                                        .dayOfWeekList(List.of(DayOfWeek.THURSDAY, DayOfWeek.SUNDAY))
                                        .build(),
                                TimeRangeDto.builder()
                                        .startTime(LocalTime.of(11, 40))
                                        .endTime(LocalTime.of(11, 59))
                                        .minHeadCount(1)
                                        .maxHeadCount(2)
                                        .price(1000)
                                        .build()
                                )
                        ).build();

        Integer res = batchCreationOfSessionUseCase.batchCreationOfSession(batchCreationOfSessionDto);
    }

}