package kr.co.apfactory.storesolution.domain.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_temp_order_basis")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class TempOrderBasis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("시퀀스")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("주문 공통 시퀀스")
    private TempOrderCommon orderCommon;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("제작방식")
    private CommonCodeChild makingType;

    @Column(length = 200)
    @Comment("담당자 이름")
    private String picName;

    @Column(length = 200)
    @Comment("체촌자 이름")
    private String measurePic;

    @Column(columnDefinition = "decimal(3) default 0", nullable = false)
    @Comment("키")
    private BigDecimal height;

    @Column(columnDefinition = "decimal(3) default 0", nullable = false)
    @Comment("몸무게")
    private BigDecimal weight;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("나이대")
    private CommonCodeChild ageGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("일정필수")
    private CommonCodeChild schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("선택단추")
    private CommonCodeChild button;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("안감")
    private CommonCodeChild lining;

    @Column(length = 100)
    @Comment("원단 회사")
    private String fabricCompany;

    @Column(length = 100)
    @Comment("원단 품번")
    private String fabricPattern;

    @Column(length = 100)
    @Comment("원단 색상")
    private String fabricColor;

    @Column(columnDefinition = "decimal(3,1) default 0", nullable = false)
    @Comment("요척")
    private BigDecimal yard;

    @Column(length = 800)
    @Comment("주문 하단 기타 요청사항")
    private String etcMemo;
}
