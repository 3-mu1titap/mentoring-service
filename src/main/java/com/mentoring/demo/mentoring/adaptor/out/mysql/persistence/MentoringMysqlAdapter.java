package com.mentoring.demo.mentoring.adaptor.out.mysql.persistence;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.mapper.mentoringEntityMapper;
import com.mentoring.demo.mentoring.adaptor.out.mysql.repository.MentoringJpaRepository;
import com.mentoring.demo.mentoring.adaptor.out.mysql.repository.MentoringSessionJpaRepository;
import com.mentoring.demo.mentoring.application.port.in.dto.MentoringSessionAddAfterDto;
import com.mentoring.demo.mentoring.application.port.out.MentoringRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Component("MentoringMysqlAdapter")
public class MentoringMysqlAdapter implements MentoringRepositoryOutPort {
    private final MentoringJpaRepository mentoringJpaRepository;
    private final MentoringSessionJpaRepository mentoringSessionJpaRepository;

    @Override
    public MentoringAddAfterOutDto createMentoring(MentoringAddRequestOutDto mentoringAddRequestOutDto) {
        // 멘토링 저장
        MentoringEntity mentoring = mentoringJpaRepository.save(mentoringAddRequestOutDto.toEntity());

        return mentoringEntityMapper.toMentoringAddAfterOutDto(mentoring);
    }

    @Override
    public List<MentoringSessionAddAfterDto> createMentoringSession(
            MentoringAddAfterOutDto mentoringAddAfterOutDto,
            List<MentoringSessionOutDto> sessionOutDto)
    {
        // 멘토링 세션 저장
        List<MentoringSessionEntity> mentoringSessionEntities =
                mentoringSessionJpaRepository.saveAll(MentoringSessionOutDto.toEntities(mentoringAddAfterOutDto, sessionOutDto));

        return mentoringEntityMapper.toMentoringSessionAddAfterDto(mentoringSessionEntities);
    }

    @Override
    public void updateMentoring(MentoringEditTransactionDto mentoringEditTransactionDto) {
        MentoringEntity updatedMentoring = mentoringEntityMapper.toMentoring(mentoringEditTransactionDto);
        log.info("멘토링 수정 어댑터 : "+updatedMentoring);
        mentoringJpaRepository.save(updatedMentoring);

    }

    @Override
    public MentoringResponseOutDto findByMentoringUuid(String mentoringUuid) {
        return mentoringEntityMapper.toMentoringResponseOutDto(mentoringJpaRepository.findByMentoringUuid(mentoringUuid));
    }


}

