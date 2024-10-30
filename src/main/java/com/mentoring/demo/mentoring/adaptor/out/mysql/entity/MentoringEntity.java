package com.mentoring.demo.mentoring.adaptor.out.mysql.entity;

import com.mentoring.demo.mentoring.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@Entity(name = "mentoring")
@ToString
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

    @Column
    @Comment("썸네일 url")
    private String thumbnailUrl;

    @Column(nullable = false)
    @Comment("내용 재사용 여부")
    private Boolean isReusable;

    @Column(nullable = false)
    @Comment("삭제 여부")
    private Boolean isDeleted;



    @Builder
    public MentoringEntity(Long id, String mentoringUuid, String name, String detail, String mentorUuid,
                           String thumbnailUrl, Boolean isReusable, Boolean isDeleted) {
        this.id = id;
        this.mentoringUuid = mentoringUuid;
        this.name = name;
        this.detail = detail;
        this.mentorUuid = mentorUuid;
        this.thumbnailUrl = thumbnailUrl;
        this.isReusable = isReusable;
        this.isDeleted = isDeleted;
    }
}
