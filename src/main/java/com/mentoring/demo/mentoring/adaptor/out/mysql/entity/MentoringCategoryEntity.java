package com.mentoring.demo.mentoring.adaptor.out.mysql.entity;

import com.mentoring.demo.mentoring.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "mentoring_category_list")
@ToString
public class MentoringCategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topCategoryCode;
    private String middleCategoryCode;
    private String bottomCategoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentoring_id")
    private MentoringEntity mentoringEntity;

    private String mentoringUuid;
    @Builder
    public MentoringCategoryEntity(String topCategoryCode, String middleCategoryCode, String bottomCategoryCode,
                                   MentoringEntity mentoringEntity, String mentoringUuid) {
        this.topCategoryCode = topCategoryCode;
        this.middleCategoryCode = middleCategoryCode;
        this.bottomCategoryCode = bottomCategoryCode;
        this.mentoringEntity = mentoringEntity;
        this.mentoringUuid = mentoringUuid;
    }
}
