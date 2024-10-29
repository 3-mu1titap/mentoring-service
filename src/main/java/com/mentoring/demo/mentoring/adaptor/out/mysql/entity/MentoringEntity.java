package com.mentoring.demo.mentoring.adaptor.out.mysql.entity;

import com.mentoring.demo.mentoring.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "mentoring")
public class MentoringEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @Comment("멘토링 Uuid")
    private String mentoringUuid;

    @Column(nullable = false, length = 50)
    @Comment("멘토링 이름")
    private String name;

    @Column(nullable = false, columnDefinition = "LONGTEXT") // 에디터 데이터(html)
    @Comment("멘토링 상세정보")
    private String detail;

    @Column(nullable = false, length = 50)
    @Comment("멘토 Uuid")
    private String mentorUuid;

    @Column(nullable = false)
    @Comment("내용 재사용 여부")
    private Boolean isReusable;

    @Column
    @Comment("썸네일 url")
    private String thumbnailUrl;

    @Builder
    public MentoringEntity(String mentoringUuid, String name, String detail, String mentorUuid,
                           Boolean isReusable, String thumbnailUrl) {
        this.mentoringUuid = mentoringUuid;
        this.name = name;
        this.detail = detail;
        this.mentorUuid = mentorUuid;
        this.isReusable = isReusable;
        this.thumbnailUrl = thumbnailUrl;
    }
}
