package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttach;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_pattern")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class Pattern extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("패턴 시퀀스")
    private Long id;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부")
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("패턴 부위 타입 (상의 : 20, 하의 : 21, 조끼 : 22, 코트 : 23)")
    private CodeType type;

    @Column(length = 200)
    @Comment("패턴명 - 중국어")
    private String patternNameCn;

    @Column(length = 200)
    @Comment("패턴명 - 영어")
    private String patternNameEn;

    @Column(length = 200)
    @Comment("패턴명 - 인도네시아어")
    private String patternNameIn;

    @Column(length = 200)
    @Comment("패턴명 - 일본어")
    private String patternNameJp;

    @Column(length = 200)
    @Comment("패턴명 - 한국어")
    private String patternNameKo;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("이미지")
    private FileAttach fileAttach;

    @ManyToOne(fetch = FetchType.LAZY)
    private FileAttach fileAttachLeftBlack;

    @ManyToOne(fetch = FetchType.LAZY)
    private FileAttach fileAttachRightBlack;

    @Column(columnDefinition = "tinyint default 99", nullable = false)
    @Comment("노출 순서")
    private Integer showSequence;
}
