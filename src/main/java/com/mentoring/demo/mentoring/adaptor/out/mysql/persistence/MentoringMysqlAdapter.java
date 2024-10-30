package com.mentoring.demo.mentoring.adaptor.out.mysql.persistence;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.mapper.mentoringEntityMapper;
import com.mentoring.demo.mentoring.adaptor.out.mysql.repository.MentoringJpaRepository;
import com.mentoring.demo.mentoring.adaptor.out.mysql.repository.MentoringSessionJpaRepository;
import com.mentoring.demo.mentoring.application.port.out.MentoringRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringEditTransactionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringSessionTransactionDto;
import com.mentoring.demo.mentoring.application.port.out.dto.MentoringTransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@Component("MentoringMysqlAdapter")
public class MentoringMysqlAdapter implements MentoringRepositoryOutPort {
    private final MentoringJpaRepository mentoringJpaRepository;
    private final MentoringSessionJpaRepository mentoringSessionJpaRepository;

    @Override
    public void createMentoring(MentoringTransactionDto mentoringTransactionDto) {
        // 멘토링 저장
        log.info("멘토링 저장 어댑터 : "+mentoringTransactionDto);
        MentoringEntity mentoring = mentoringJpaRepository.save(mentoringTransactionDto.toEntity());
        // 멘토링 세션 저장
        mentoringSessionJpaRepository
                .saveAll(mentoringTransactionDto
                .toSessionEntity(mentoringTransactionDto.getSessionList(), mentoring));
    }

    @Override
    public void updateMentoring(MentoringEditTransactionDto mentoringEditTransactionDto) {
        // 멘토링 수정
        // todo : 이정도 조회는 허용?
        MentoringEntity findMentoring
                = mentoringJpaRepository.findByMentoringUuid(mentoringEditTransactionDto.getUuid());
        MentoringEntity updatedMentoring =
                mentoringEntityMapper.toMentoring(findMentoring, mentoringEditTransactionDto);
        mentoringJpaRepository.save(updatedMentoring);

    }


}

