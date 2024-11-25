package com.mentoring.demo.mentoring.adaptor.out.mysql.repository;

import com.mentoring.demo.mentoring.application.port.in.dto.in.TimeRangeDto;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringSessionOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeDtoByDateDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.TimeRangeOutDto;
import com.mentoring.demo.mentoring.domain.vo.TimeRange;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mentoring.demo.mentoring.adaptor.out.mysql.entity.QMentoringSessionEntity.mentoringSessionEntity;

@Repository
@RequiredArgsConstructor
@Log4j2
public class MentoringSessionDslRepositoryImpl implements MentoringSessionDslRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public Map<LocalDate, List<TimeRange>>  getSessionTimeUntilDeadline(String mentoringId, LocalDate deadLineDate) {
        Long mentoringIdLong = Long.parseLong(mentoringId);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Tuple> fetch = queryFactory.select(mentoringSessionEntity.startDate, mentoringSessionEntity.startTime, mentoringSessionEntity.endTime,
                        new CaseBuilder() // sql case문
                                .when(mentoringSessionEntity.startTime.gt(mentoringSessionEntity.endTime)).then(true)
                                .otherwise(false)
                )
                .from(mentoringSessionEntity)
                .where(mentoringSessionEntity.mentoringEntity.id.eq(mentoringIdLong)
                        .and(mentoringSessionEntity.startDate.goe(tomorrow))
                        .and(mentoringSessionEntity.endDate.loe(deadLineDate)) // 내일~마감일 사이까지으 기존 세션 조회
                        .and(mentoringSessionEntity.isDeleted.isFalse())
                )
                .orderBy(mentoringSessionEntity.startDate.asc(), mentoringSessionEntity.startTime.asc())
                .fetch();
        return fetch.stream()
                .collect(Collectors.groupingBy( // startDate 기준으로 그룹화
                        tuple -> tuple.get(mentoringSessionEntity.startDate),
                        LinkedHashMap::new,//정렬된 결과 유지
                        Collectors.mapping(
                                tuple -> TimeRange.builder()
                                        .startTime(tuple.get(mentoringSessionEntity.startTime))
                                        .endTime(tuple.get(mentoringSessionEntity.endTime))
                                        .isNextDay(tuple.get(new CaseBuilder()
                                                .when(mentoringSessionEntity.startTime.gt(mentoringSessionEntity.endTime)).then(true)
                                                .otherwise(false)))
                                        .build(),
                                Collectors.toList()
                        )
                ));

    }

    @Override
    public boolean existsMentoringSession(String mentoringId, MentoringSessionOutDto dto) {
        Long mentoringIdLong = Long.parseLong(mentoringId);
        Integer fetchOne = queryFactory
                .selectOne()
                .from(mentoringSessionEntity)
                .where(
                        mentoringSessionEntity.mentoringEntity.id.eq(mentoringIdLong)
                        .and(mentoringSessionEntity.isDeleted.isFalse())
                        .and(
                                mentoringSessionEntity.startDate.before(dto.getEndDate())
                                .or(
                                    mentoringSessionEntity.startDate.eq(dto.getEndDate())
                                    .and(mentoringSessionEntity.startTime.lt(dto.getEndTime()))
                                )
                        )
                        .and(
                                mentoringSessionEntity.endDate.after(dto.getStartDate())
                                .or(
                                    mentoringSessionEntity.endDate.eq(dto.getStartDate())
                                    .and(mentoringSessionEntity.endTime.gt(dto.getStartTime()))
                                )
                        )
                )
                .fetchFirst();

        return fetchOne != null;

    }

}
