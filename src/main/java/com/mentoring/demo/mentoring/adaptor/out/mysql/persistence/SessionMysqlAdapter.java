package com.mentoring.demo.mentoring.adaptor.out.mysql.persistence;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringSessionEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.mapper.mentoringEntityMapper;
import com.mentoring.demo.mentoring.adaptor.out.mysql.repository.MentoringSessionJpaRepository;
import com.mentoring.demo.mentoring.application.port.out.MentoringSessionRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.in.*;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionResponseOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.SessionTimeResponseOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@Component("SessionMysqlAdapter")
public class SessionMysqlAdapter implements MentoringSessionRepositoryOutPort {
    private final MentoringSessionJpaRepository mentoringSessionJpaRepository;
    @Override
    public List<MentoringSessionAddAfterOutDto> createMentoringSession(MentoringAddAfterOutDto mentoringAddAfterOutDto,
                                                                       MentoringAddRequestOutDto mentoringAddRequestOutDto) {
        // 멘토링 세션 저장
        List<MentoringSessionEntity> mentoringSessionEntities =
                mentoringSessionJpaRepository.saveAll(
                        MentoringSessionOutDto.toEntities(mentoringAddAfterOutDto, mentoringAddRequestOutDto));

        return mentoringEntityMapper.toMentoringSessionAddAfterDto(mentoringSessionEntities);
    }


    @Override
    public SessionResponseOutDto getSessionResponseOutByUuid(String uuid) {
        return SessionResponseOutDto.from(mentoringSessionJpaRepository.findByUuid(uuid));
    }

    @Override
    public SessionTimeResponseOutDto getSessionTimeOutDtoByUuid(String uuid) {
        return SessionTimeResponseOutDto.from(mentoringSessionJpaRepository.findByUuid(uuid));
    }

    @Override
    public void closeSession(String uuid) {
        mentoringSessionJpaRepository.updateIsClosedByUuid(uuid);
    }

    @Override
    public SessionTimeResponseOutDto validateSessionTime(
            SessionValidationRequestOutDto dto
    )
    {
        Optional<MentoringSessionEntity> oneValidSessionTime =
                mentoringSessionJpaRepository.findOneValidSessionTime(dto.getStartDate(), dto.getStartTime(), dto.getEndDate(), dto.getEndTime(), dto.getMentorUuid());

        return oneValidSessionTime.map(SessionTimeResponseOutDto::from).orElse(null);

    }
}
