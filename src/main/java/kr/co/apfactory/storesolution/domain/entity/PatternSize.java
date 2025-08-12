package kr.co.apfactory.storesolution.domain.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_pattern_size")
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class PatternSize extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("패턴 사이즈 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("패턴")
    private Pattern pattern;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부")
    private Boolean deleted;

    @Column(columnDefinition = "int", nullable = false)
    @Comment("사이즈 구분")
    private Integer sizeType;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 상동, 하의 : 허리, 조끼 : 조끼장, 코트 : 상동")
    private BigDecimal data01;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 상앞품, 하의 : 힙, 조끼 : 상동, 코트 : 상앞품")
    private BigDecimal data02;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 상뒷품, 하의 : 허벅, 조끼 : 중동, 코트 : 상뒷품")
    private BigDecimal data03;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 중동, 하의 : 앞마다, 조끼 : 사용안함, 코트 : 중동")
    private BigDecimal data04;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 중앞품, 하의 : 뒷마다, 조끼 : 사용안함, 코트 : 중앞품")
    private BigDecimal data05;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 중뒷품, 하의 : 무릎, 조끼 : 사용안함, 코트 : 중뒷품")
    private BigDecimal data06;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 어깨, 하의 : 부리/2, 조끼 : 사용안함, 코트 : 어깨")
    private BigDecimal data07;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 소매(좌), 하의 : 총기장, 조끼 : 사용안함, 코트 : 소매(좌)")
    private BigDecimal data08;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 소매(우), 하의 : , 조끼 : 사용안함, 코트 : 소매(우)")
    private BigDecimal data09;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 총장(뒷기장), 하의 : 사용안함, 조끼 : 사용안함, 코트 : 총장")
    private BigDecimal data10;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 앞기장, 하의 : 사용안함, 조끼 : 사용안함, 코트 : 앞기장")
    private BigDecimal data11;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 소매통상박, 하의 : 사용안함, 조끼 : 사용안함, 코트 : 소매통상박")
    private BigDecimal data12;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("상의 : 소매통부리, 하의 : 사용안함, 조끼 : 사용안함, 코트 : 소매통부리")
    private BigDecimal data13;

    @Column(columnDefinition = "decimal(3,1) default 0", nullable = false)
    @Comment("요척")
    private BigDecimal yard;

    @Column(columnDefinition = "int default 99", nullable = false)
    @Comment("노출 순서")
    private Integer showSequence;
}
