package com.mentoring.demo.mentoring.adaptor.out.mysql.mapper;

import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringEntity;
import com.mentoring.demo.mentoring.adaptor.out.mysql.entity.MentoringHashTagEntity;
import com.mentoring.demo.mentoring.application.port.out.dto.in.MentoringHashtagOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.AfterHashtag;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringAddAfterOutDto;
import com.mentoring.demo.mentoring.application.port.out.dto.out.MentoringHashTagAfterOutDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MentoringHashtagMapper {

    /**
     * 해시태그
     */
    public static List<MentoringHashTagEntity> of (MentoringAddAfterOutDto mentoringAddAfterOutDto,
                                                   List<MentoringHashtagOutDto> hashtagOutDtos)
    {
        MentoringEntity mentoringEntity = MentoringEntity.builder()
                .id(Long.valueOf(mentoringAddAfterOutDto.getMentoringId()))
                .mentoringUuid(mentoringAddAfterOutDto.getMentoringUuid())
                .name(mentoringAddAfterOutDto.getName())
                .detail(mentoringAddAfterOutDto.getDetail())
                .mentorUuid(mentoringAddAfterOutDto.getMentorUuid())
                .thumbnailUrl(mentoringAddAfterOutDto.getThumbnailUrl())
                .isReusable(mentoringAddAfterOutDto.getIsReusable())
                .isDeleted(mentoringAddAfterOutDto.getIsDeleted())
                .build();

        return hashtagOutDtos.stream().map(hashtagOutDto -> MentoringHashTagEntity.builder()
                        .mentoringEntity(mentoringEntity)
                        .hashtagId(Long.valueOf(hashtagOutDto.getHashtagId()))
                        .hashtagName(hashtagOutDto.getHashtagName())
                        .mentoringUuid(mentoringEntity.getMentoringUuid())
                        .build())
                .toList();
    }
    public static MentoringHashTagAfterOutDto ofMentoringHashTagAfterOutDto (MentoringAddAfterOutDto mentoringAddAfterOutDto,
                                                  List<MentoringHashTagEntity> mentoringHashTagEntities){
        return MentoringHashTagAfterOutDto.builder()
                .mentoringId(mentoringAddAfterOutDto.getMentoringId())
                .afterHashtagList(
                        mentoringHashTagEntities.stream().map(mentoringHashTagEntity -> AfterHashtag.builder()
                                .hashtagId(mentoringHashTagEntity.getHashtagId().toString())
                                .hashtagName(mentoringHashTagEntity.getHashtagName())
                                .build())
                                .toList()
                )
                .build();

    }


}
