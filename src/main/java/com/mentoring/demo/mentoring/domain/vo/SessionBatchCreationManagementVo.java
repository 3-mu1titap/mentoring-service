package com.mentoring.demo.mentoring.domain.vo;

import com.mentoring.demo.mentoring.application.port.in.dto.in.TimeRangeDto;
import com.mentoring.demo.mentoring.application.port.in.dto.in.TimeSlotDto;
import com.mentoring.demo.mentoring.common.entity.BaseResponseStatus;
import com.mentoring.demo.mentoring.common.exception.BaseException;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Builder
@ToString
@Log4j2
public class SessionBatchCreationManagementVo {


    // 도메인 로직 : 요일 중복 체크, 같은 요일에 겹치는 시간 존재하는지 체크


    public static void validateDeadlineDate(LocalDate creationEndDate) {
        // 생성마감일은 오늘로부터 90일 뒤까지의 날짜만 가능
        LocalDate maxDeadlineDate = LocalDate.now().plusDays(90);
        if (!creationEndDate.isAfter(LocalDate.now()) || creationEndDate.isAfter(maxDeadlineDate)) {
            throw new BaseException(BaseResponseStatus.DEADLINE_DATE_CONFLICT);
        }
    }

    public static Map<LocalDate, List<TimeRange>> convertSessionListByDateUntilDeadline(
            List<TimeRangeDto> timeRanges, LocalDate creationStartDate, LocalDate creationEndDate)
    {
        Map<LocalDate, List<TimeRange>> result = new TreeMap<>();

        for (TimeRangeDto timeRangeDto : timeRanges) { // 시간대 순회
            boolean isEveryDay = timeRangeDto.getDayOfWeekList() == null; // 요일 리스트 비면 매일 생성
            LocalDate dateCursor = creationStartDate;
            // creationEndDate 일까지
            while (!dateCursor.isAfter(creationEndDate)) {
                // 매일 생성이거나 해당 요일이면
                if (isEveryDay || timeRangeDto.getDayOfWeekList().contains(dateCursor.getDayOfWeek())) {
                    List<TimeRange> timeRangeList = result.getOrDefault(dateCursor, new ArrayList<>());
                    timeRangeList.add(TimeRange.builder()
                            .startDate(dateCursor)
                            .startTime(timeRangeDto.getStartTime())
                            .endDate(timeRangeDto.getStartTime().isAfter(timeRangeDto.getEndTime()) ?
                                    dateCursor.plusDays(1) : dateCursor)
                            .endTime(timeRangeDto.getEndTime())
                            .minHeadCount(timeRangeDto.getMinHeadCount())
                            .maxHeadCount(timeRangeDto.getMaxHeadCount())
                            .price(timeRangeDto.getPrice())
                            .build());
                    result.put(dateCursor, timeRangeList);
                }
                dateCursor = dateCursor.plusDays(1);
            }
        }
        // result 날짜별로 timeRange 출력
        if(!result.isEmpty()){
            log.info("=====================생성된 세션====================");
            for (LocalDate date : result.keySet()) {
                log.info(date + " (" + date.getDayOfWeek() + ")");
                for (TimeRange timeRange : result.get(date)) {
                    log.info(timeRange.getStartDate()+" => "+timeRange.getStartTime() + " ~ " +timeRange.getEndDate()+" => "+ timeRange.getEndTime() );
                }
            }
        }


        return result;
    }
    public static Map<LocalDate, List<TimeRange>>  filterDuplicateSessions (
            Map<LocalDate, List<TimeRange>> inputMap, Map<LocalDate, List<TimeRange>> dbMap)
    {
        Map<LocalDate, List<TimeRange>> result = new TreeMap<>();

        //inputMap 순회
        for (LocalDate inputDate : inputMap.keySet()) {
            List<TimeRange> inputTimeRanges = inputMap.get(inputDate);
            // db에 없는 날짜라면 해당 날짜 전부 넣음
            if(!dbMap.containsKey(inputDate)) {
                log.info("db에 없는 날짜라 해당 날짜 전부 넣음 inputDate  : " + inputDate);
                result.put(inputDate, inputTimeRanges);
                continue;
            }
            else{
                // dbdate에 해당하는 시간대 리스트
                List<TimeRange> timeRanges = dbMap.get(inputDate);
            }

        }

        // 결과
        if(!result.isEmpty()){
            log.info("=====================안겹침====================");
            for (LocalDate date : result.keySet()) {
                log.info(date + " (" + date.getDayOfWeek() + ")");
                for (TimeRange timeRange : result.get(date)) {
                    log.info(timeRange.getStartTime() + " ~ " + timeRange.getEndTime() + " => ");
                }
            }
        }
        return result;

    }

}


