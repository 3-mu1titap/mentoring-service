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


    public static void validateDeadlineDate(LocalDate deadlineDate) {
        // 생성마감일은 오늘로부터 90일 뒤까지의 날짜만 가능
        LocalDate maxDeadlineDate = LocalDate.now().plusDays(90);
        if (!deadlineDate.isAfter(LocalDate.now()) || deadlineDate.isAfter(maxDeadlineDate)) {
            throw new BaseException(BaseResponseStatus.DEADLINE_DATE_CONFLICT);
        }
    }

    public static Map<LocalDate, List<TimeRange>> convertSessionListByDateUntilDeadline(
            List<TimeSlotDto> timeSlotList, LocalDate deadLineDate) {
        Map<LocalDate, List<TimeRange>> result = new TreeMap<>();
        // 내일 날짜부터 시작
        LocalDate dateCursor = LocalDate.now().plusDays(1);

        // 생성 마감일까지 세션 생성
        while (!dateCursor.isAfter(deadLineDate)) {
            DayOfWeek todayWeek = dateCursor.getDayOfWeek(); // 현재 요일

            for (TimeSlotDto timeSlot : timeSlotList) {
                // 현재 요일이면
                if (timeSlot.getDayOfWeek() == todayWeek) {
                    // 해당 요일의 TimeRange 리스트 가져오기
                    List<TimeRangeDto> timeRangeDtos = timeSlot.getTimeRanges();
                    List<TimeRange> timeRanges = timeRangeDtos.stream()
                            .map(TimeRangeDto::toTimeRange)
                            .sorted(
                                    Comparator.comparing(TimeRange::getIsNextDay).reversed() // 1순위: 다음날로 넘어가는 시간
                                            .thenComparing(TimeRange::getStartTime) // 2순위: 시작 시간 순으로 정렬
                            )
                            .toList();
                    result.computeIfAbsent(dateCursor, k -> new ArrayList<>()).addAll(timeRanges);
                }
            }
            dateCursor = dateCursor.plusDays(1);
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
                    log.info(timeRange.getStartTime() + " ~ " + timeRange.getEndTime() + " => " +timeRange.getIsNextDay());
                }
            }
        }
        return result;

    }

}


