package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_common_code_child")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CommonCodeChild extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("하위 공통 코드 시퀀스")
    private Long id;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부")
    private Boolean deleted;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("기본값 여부")
    private Boolean defaultValue;

    @Column(columnDefinition = "tinyint default 1", nullable = false)
    @Comment("노출 순서")
    private Integer showSequence;

    @Column(length = 8, nullable = false, unique = true)
    @Comment("코드값")
    private String childCodeValue;

    @Column(columnDefinition = "decimal(2,1) default 0", nullable = false)
    @Comment("요척")
    private BigDecimal yard;

    @Column(length = 120, nullable = false)
    @Comment("코드명 - 한국어")
    private String codeNameKo;

    @Column(length = 120, nullable = false)
    @Comment("코드명 - 영어")
    private String codeNameEn;

    @Column(length = 120, nullable = false)
    @Comment("코드명 - 중국어")
    private String codeNameCn;

    @Column(length = 120, nullable = false)
    @Comment("코드명 - 일본어")
    private String codeNameJp;

    @Column(length = 120, nullable = false)
    @Comment("코드명 - 인도네시아어")
    private String codeNameIn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code_value")
    private CommonCodeParent parentCode;

    @OneToOne(fetch = FetchType.LAZY)
    private FileAttach fileAttach;

    @OneToOne(fetch = FetchType.LAZY)
    private FileAttach fileAttachLeftBlack;

    @OneToOne(fetch = FetchType.LAZY)
    private FileAttach fileAttachRightBlack;

    @OneToOne(fetch = FetchType.LAZY)
    private FileAttach fileAttachStoreSolution;
}
