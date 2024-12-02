package com.mentoring.demo.mentoring.adaptor.out.mysql.persistence;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringCategoryEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringHashTagEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.mapper.MentoringHashtagMapper;
import com.mentoring.demo.mentoring.adaptor.out.mysql.mapper.mentoringEntityMapper;
import com.mentoring.demo.mentoring.adaptor.out.mysql.repository.MentoringCategoryJpaRepository;
import com.mentoring.demo.mentoring.adaptor.out.mysql.repository.MentoringHashtagJpaRepository;
import com.mentoring.demo.mentoring.adaptor.out.mysql.repository.MentoringJpaRepository;
import com.mentoring.demo.mentoring.application.port.out.MentoringHashtagRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.MentoringInquiryOutPort;
import com.mentoring.demo.mentoring.application.port.out.MentoringRepositoryOutPort;
import com.mentoring.demo.mentoring.application.port.out.dto.in.*;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringCategoryAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringHashTagAfterOutDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Component("MentoringMysqlAdapter")
public class MentoringMysqlAdapter implements MentoringRepositoryOutPort , MentoringInquiryOutPort, MentoringHashtagRepositoryOutPort {
    private final MentoringJpaRepository mentoringJpaRepository;
    private final MentoringCategoryJpaRepository mentoringCategoryJpaRepository;
    private final MentoringHashtagJpaRepository mentoringHashtagJpaRepository;

    @Override
    public MentoringAddAfterOutDto createMentoring(MentoringAddRequestOutDto mentoringAddRequestOutDto) {
        // 멘토링 저장
        MentoringEntity mentoring = mentoringJpaRepository.save(mentoringAddRequestOutDto.toEntity());
        return mentoringEntityMapper.from(mentoring);
    }

    @Override
    public List<MentoringCategoryAfterOutDto> createMentoringCategory(MentoringAddAfterOutDto mentoringAddAfterOutDto,
                                                                      MentoringAddRequestOutDto mentoringAddRequestOutDto) {
        // 멘토링 카테고리 저장
        List<MentoringCategoryEntity> mentoringCategoryEntities = mentoringCategoryJpaRepository.saveAll
                (MentoringAddRequestOutDto.toMentoringCategoryEntity(mentoringAddAfterOutDto, mentoringAddRequestOutDto.getCategoryList()));

        return mentoringEntityMapper.toMentoringCategoryAfterOutDto(mentoringCategoryEntities, mentoringAddRequestOutDto.getCategoryList());
    }



    @Override
    public List<MentoringCategoryAfterOutDto> updateMentoring(MentoringEditRequestOutDto mentoringEditRequestOutDto) {
        MentoringEntity updatedMentoring = mentoringEntityMapper.from(mentoringEditRequestOutDto);
        // 멘토링 수정
        MentoringEntity mentoring = mentoringJpaRepository.save(updatedMentoring);

        // 카테고리 저장
        List<MentoringCategoryEntity> mentoringCategoryEntities = null;
        if(mentoringEditRequestOutDto.getCategoryList() != null) {
            mentoringCategoryEntities =
                    mentoringCategoryJpaRepository.saveAll(MentoringEditRequestOutDto.of(mentoring, mentoringEditRequestOutDto.getCategoryList()));
        }

        // 해시태그 저장
        if(mentoringEditRequestOutDto.getHashTag() != null)
            mentoringHashtagJpaRepository.saveAll(MentoringEditRequestOutDto.of(mentoring, mentoringEditRequestOutDto.getHashTag()));

        return mentoringEntityMapper.of(mentoringCategoryEntities, mentoringEditRequestOutDto.getCategoryList());
    }


    @Override
    public MentoringResponseOutDto findByMentoringUuid(String mentoringUuid) {
        return mentoringEntityMapper.toMentoringResponseOutDto(mentoringJpaRepository.findByMentoringUuid(mentoringUuid));
    }

    @Override
    public void deleteMentoringCategory(String mentoringId) {
        mentoringCategoryJpaRepository.deleteByMentoringEntityId(Long.valueOf(mentoringId));
    }

    /**
     * MentoringInquiryOutPort
     */
    @Override
    public String getMentoringIdByMentoringUuid(String mentoringUuid) {
        return mentoringJpaRepository.getIdByMentoringUuid(mentoringUuid);
    }

    @Override
    public MentoringResponseOutDto getMentoringResponseByMentoringUuid(String mentoringUuid) {
        MentoringEntity mentoringEntity = mentoringJpaRepository.findByMentoringUuid(mentoringUuid);
        return MentoringResponseOutDto.builder()
                .id(mentoringEntity.getId().toString())
                .uuid(mentoringEntity.getMentoringUuid())
                .name(mentoringEntity.getName())
                .description(mentoringEntity.getDescription())
                .detail(mentoringEntity.getDetail())
                .isReusable(mentoringEntity.getIsReusable())
                .thumbnailUrl(mentoringEntity.getThumbnailUrl())
                .mentorUuid(mentoringEntity.getMentorUuid())
                .isDeleted(mentoringEntity.getIsDeleted())
                .build();

    }


    @Override
    public MentoringHashTagAfterOutDto createMentoringHashtag(MentoringAddAfterOutDto mentoringAddAfterOutDto,
                                                              List<MentoringHashtagOutDto> MentoringHashtagOutDto)
    {

        List<MentoringHashTagEntity> mentoringHashTagEntities =
                mentoringHashtagJpaRepository.saveAll(MentoringHashtagMapper.of(mentoringAddAfterOutDto, MentoringHashtagOutDto));

        return MentoringHashtagMapper.ofMentoringHashTagAfterOutDto(mentoringAddAfterOutDto,mentoringHashTagEntities);
    }

    @Override
    public void deleteMentoringHashtag(String mentoringId) {
        mentoringHashtagJpaRepository.deleteByMentoringEntityId(Long.valueOf(mentoringId));
    }
}

