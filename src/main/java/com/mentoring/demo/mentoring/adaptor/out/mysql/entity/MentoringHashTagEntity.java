package com.mentoring.demo.mentoring.adaptor.out.mysql.entity;

import com.mentoring.demo.mentoring.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mentoring_hashtag_list")
@ToString
@Builder
public class MentoringHashTagEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentoring_id")
    private MentoringEntity mentoringEntity;

    private String mentoringUuid;

    private Long hashtagId;
    private String hashtagName;



}
