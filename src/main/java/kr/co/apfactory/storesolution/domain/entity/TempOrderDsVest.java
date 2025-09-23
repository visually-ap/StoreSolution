package kr.co.apfactory.storesolution.domain.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_temp_order_ds_vest")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class TempOrderDsVest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("시퀀스")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("주문 공통 시퀀스")
    private TempOrderCommon orderCommon;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("조끼 패턴 시퀀스")
    private Pattern vestPattern;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("조끼 게이지복 시퀀스")
    private PatternSize vestGauge;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("뒷판")
    private CommonCodeChild vestBack;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("앞주머니")
    private CommonCodeChild vestFrontPocket;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("가슴포켓")
    private CommonCodeChild vestChestPocket;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("라펠")
    private CommonCodeChild vestLapel;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("amf 호시")
    private CommonCodeChild vestAmf;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 뒷기장")
    private BigDecimal vestSizeRealInchBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 뒷기장")
    private BigDecimal vestSizeRealCentiBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 뒷기장")
    private BigDecimal vestSizeEditBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 상동")
    private BigDecimal vestSizeRealInchChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상동")
    private BigDecimal vestSizeRealCentiChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상동")
    private BigDecimal vestSizeEditChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 중동")
    private BigDecimal vestSizeRealInchWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중동")
    private BigDecimal vestSizeRealCentiWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중동")
    private BigDecimal vestSizeEditWaist;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("굴신")
    private BigDecimal vestSizeFrontRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("반신")
    private BigDecimal vestSizeBackRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("견도")
    private BigDecimal vestSizeShoulderSlope;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷고대")
    private BigDecimal vestSizeBackNeckline;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷목점이동")
    private BigDecimal vestSizeBackNeckPointMove;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("목둘레")
    private BigDecimal vestSizeNeckRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("우찌")
    private BigDecimal vestSizeChestLine;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("역V존")
    private BigDecimal vestSizeReverseZone;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("앞기장")
    private BigDecimal vestSizeFrontLength;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("첫단추")
    private BigDecimal vestSizeFirstButton;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("암홀수정")
    private BigDecimal vestSizeArmHole;

    public void updateOrderCommon(TempOrderCommon orderCommon) {
        this.orderCommon = orderCommon;
    }
}
