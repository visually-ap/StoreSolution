package kr.co.apfactory.storesolution.domain.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_temp_order_ds_pants")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class TempOrderDsPants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("시퀀스")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("주문 공통 시퀀스")
    private TempOrderCommon orderCommon;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("하의 패턴 시퀀스")
    private Pattern pantsPattern;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("게이지복")
    private PatternSize pantsGauge;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("벨트고리")
    private CommonCodeChild pantsBeltLoop;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("주머니")
    private CommonCodeChild pantsPocket;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("밑단모양")
    private CommonCodeChild pantsHemShape;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("두께")
    private CommonCodeChild pantsHemThickness;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("비죠")
    private CommonCodeChild pantsFlap;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("(비죠) 길이")
    private CommonCodeChild pantsFlapLength;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("체크원단")
    private CommonCodeChild pantsCheckFabric;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("추가옵션 1")
    private CommonCodeChild pantsAddOption1;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("추가옵션 2")
    private CommonCodeChild pantsAddOption2;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("추가옵션 3")
    private CommonCodeChild pantsAddOption3;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("굴신")
    private BigDecimal pantsFrontRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("반신")
    private BigDecimal pantsBackRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("O자")
    private BigDecimal pantsO;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 허리")
    private BigDecimal pantsSizeRealInchWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 허리")
    private BigDecimal pantsSizeRealCentiWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 허리")
    private BigDecimal pantsSizeEditWaist;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 앞판 수정값 허리")
    private BigDecimal pantsSizeEditDetailFrontWaist;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 뒷판 수정값 허리")
    private BigDecimal pantsSizeEditDetailBackWaist;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 힙")
    private BigDecimal pantsSizeRealInchHip;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 힙")
    private BigDecimal pantsSizeRealCentiHip;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 힙")
    private BigDecimal pantsSizeEditHip;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 앞판 수정값 힙")
    private BigDecimal pantsSizeEditDetailFrontHip;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 뒷판 수정값 힙")
    private BigDecimal pantsSizeEditDetailBackHip;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 허벅")
    private BigDecimal pantsSizeRealInchThigh;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 허벅")
    private BigDecimal pantsSizeRealCentiThigh;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 허벅")
    private BigDecimal pantsSizeEditThigh;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 앞판 수정값 허벅")
    private BigDecimal pantsSizeEditDetailFrontThigh;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 뒷판 수정값 허벅")
    private BigDecimal pantsSizeEditDetailBackThigh;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 앞상")
    private BigDecimal pantsSizeRealInchRise;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 앞상")
    private BigDecimal pantsSizeRealCentiRise;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 앞상")
    private BigDecimal pantsSizeEditRise;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("세부 수정 앞판 수정값 앞상(앞판꼬리)")
    private BigDecimal pantsSizeDetailFrontRise;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("세부 수정 뒷판 수정값 앞상(사용안함 : 예비)")
    private BigDecimal pantsSizeDetailBackRise;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 뒷상")
    private BigDecimal pantsSizeRealInchInseam;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 뒷상")
    private BigDecimal pantsSizeRealCentiInseam;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 뒷상")
    private BigDecimal pantsSizeEditInseam;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("세부 수정 앞판 수정값 뒷상(뒷판꼬리)")
    private BigDecimal pantsSizeDetailFrontInseam;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("세부 수정 뒷판 수정값 뒷상(똥꼬파기)")
    private BigDecimal pantsSizeDetailBackInseam;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 무릎")
    private BigDecimal pantsSizeRealInchNee;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 무릎")
    private BigDecimal pantsSizeRealCentiNee;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 무릎")
    private BigDecimal pantsSizeEditNee;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 앞판 수정값 무릎")
    private BigDecimal pantsSizeEditDetailFrontNee;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 뒷판 수정값 무릎")
    private BigDecimal pantsSizeEditDetailBackNee;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 부리")
    private BigDecimal pantsSizeRealInchOpening;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 부리")
    private BigDecimal pantsSizeRealCentiOpening;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 부리")
    private BigDecimal pantsSizeEditOpening;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 앞판 수정값 부리")
    private BigDecimal pantsSizeEditDetailFrontOpening;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 뒷판 수정값 부리")
    private BigDecimal pantsSizeEditDetailBackOpening;



    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 총기장")
    private BigDecimal pantsSizeRealInchGarmentLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 총기장")
    private BigDecimal pantsSizeRealCentiGarmentLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 총기장")
    private BigDecimal pantsSizeEditGarmentLength;

    public void updateOrderCommon(TempOrderCommon orderCommon) {
        this.orderCommon = orderCommon;
    }
}
