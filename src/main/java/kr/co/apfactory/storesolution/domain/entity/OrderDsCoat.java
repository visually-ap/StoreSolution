package kr.co.apfactory.storesolution.domain.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_ds_coat")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class OrderDsCoat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("시퀀스")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("주문 공통 시퀀스")
    private OrderCommon orderCommon;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("스타일")
    private CommonCodeChild coatStyle;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("단추갯수")
    private CommonCodeChild coatButtonCount;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("가슴포켓")
    private CommonCodeChild coatChestPocket;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("어깨")
    private CommonCodeChild coatShoulder;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("앞주머니")
    private CommonCodeChild coatFrontPocket;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("패드(좌)")
    private CommonCodeChild coatLeftPad;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("뒷트임")
    private CommonCodeChild coatBackSlit;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("패드(우)")
    private CommonCodeChild coatRightPad;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("소매단추")
    private CommonCodeChild coatSleeveButton;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("라펠")
    private CommonCodeChild coatLapel;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("소매")
    private CommonCodeChild coatSleeve;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("AMF(호시)")
    private CommonCodeChild coatAmf;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("라펠Q")
    private CommonCodeChild coatLapelQ;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("안감")
    private CommonCodeChild coatLining;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("체크원단")
    private CommonCodeChild coatCheckFabric;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("추가옵션1")
    private CommonCodeChild coatAddOption1;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("추가옵션2")
    private CommonCodeChild coatAddOption2;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("굴신")
    private BigDecimal coatSizeFrontRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("반신")
    private BigDecimal coatSizeBackRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("견도")
    private BigDecimal coatSizeShoulderSlope;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷고대")
    private BigDecimal coatSizeBackNeckline;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("암홀")
    private BigDecimal coatSizeArmHole;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷목점이동")
    private BigDecimal coatSizeBackNeckPointMove;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("상박")
    private BigDecimal coatSizeUpperArm;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("중박")
    private BigDecimal coatSizeMiddleArm;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("하박")
    private BigDecimal coatSizeLowerArm;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("앞기장")
    private BigDecimal coatSizeFrontLength;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷중심이동")
    private BigDecimal coatSizeBackCenterMove;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷중심줄임")
    private BigDecimal coatSizeBackCenterShorten;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("첫단추")
    private BigDecimal coatSizeFirstButton;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("목둘레")
    private BigDecimal coatSizeNeckRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("도련선")
    private BigDecimal coatSizeHemLine;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("우찌")
    private BigDecimal coatSizeChestLine;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("패턴")
    private Pattern coatPattern;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("게이지복")
    private PatternSize coatGauge;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 상동")
    private BigDecimal coatSizeRealInchChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상동")
    private BigDecimal coatSizeRealCentiChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상동")
    private BigDecimal coatSizeEditChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 상동")
    private BigDecimal coatSizeResultChest;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 상앞품")
    private BigDecimal coatSizeRealInchChestWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상앞품")
    private BigDecimal coatSizeRealCentiChestWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상앞품")
    private BigDecimal coatSizeEditChestWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 상앞품")
    private BigDecimal coatSizeResultChestWidth;



    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 상사이바")
    private BigDecimal coatSizeRealInchUpperSideBody;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상사이바")
    private BigDecimal coatSizeRealCentiUpperSideBody;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상사이바")
    private BigDecimal coatSizeEditUpperSideBody;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 상사이바")
    private BigDecimal coatSizeResultUpperSideBody;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 상뒷품")
    private BigDecimal coatSizeRealInchBackWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상뒷품")
    private BigDecimal coatSizeRealCentiBackWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상뒷품")
    private BigDecimal coatSizeEditBackWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 상뒷품")
    private BigDecimal coatSizeResultBackWidth;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 중동")
    private BigDecimal coatSizeRealInchWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중동")
    private BigDecimal coatSizeRealCentiWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중동")
    private BigDecimal coatSizeEditWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 중동")
    private BigDecimal coatSizeResultWaist;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 중앞품")
    private BigDecimal coatSizeRealInchWaistFront;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중앞품")
    private BigDecimal coatSizeRealCentiWaistFront;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중앞품")
    private BigDecimal coatSizeEditWaistFront;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 중앞품")
    private BigDecimal coatSizeResultWaistFront;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 중사이바")
    private BigDecimal coatSizeRealInchMiddleSideBody;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중사이바")
    private BigDecimal coatSizeRealCentiMiddleSideBody;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중사이바")
    private BigDecimal coatSizeEditMiddleSideBody;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 중사이바")
    private BigDecimal coatSizeResultMiddleSideBody;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 중뒷품")
    private BigDecimal coatSizeRealInchWaistBack;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중뒷품")
    private BigDecimal coatSizeRealCentiWaistBack;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중뒷품")
    private BigDecimal coatSizeEditWaistBack;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 중뒷품")
    private BigDecimal coatSizeResultWaistBack;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 어깨")
    private BigDecimal coatSizeRealInchShoulder;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 어깨")
    private BigDecimal coatSizeRealCentiShoulder;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 어깨")
    private BigDecimal coatSizeEditShoulder;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 어깨")
    private BigDecimal coatSizeResultShoulder;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 소매(좌)")
    private BigDecimal coatSizeRealInchLeftSleeve;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 소매(좌)")
    private BigDecimal coatSizeRealCentiLeftSleeve;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 소매(좌)")
    private BigDecimal coatSizeEditLeftSleeve;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 소매(좌)")
    private BigDecimal coatSizeResultLeftSleeve;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 소매(우)")
    private BigDecimal coatSizeRealInchRightSleeve;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 소매(우)")
    private BigDecimal coatSizeRealCentiRightSleeve;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 소매(우)")
    private BigDecimal coatSizeEditRightSleeve;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 소매(우)")
    private BigDecimal coatSizeResultRightSleeve;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 뒷기장")
    private BigDecimal coatSizeRealInchBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 뒷기장")
    private BigDecimal coatSizeRealCentiBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 뒷기장")
    private BigDecimal coatSizeEditBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 뒷기장")
    private BigDecimal coatSizeResultBackLength;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 앞기장")
    private BigDecimal coatSizeRealInchFrontLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 앞기장")
    private BigDecimal coatSizeRealCentiFrontLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 앞기장")
    private BigDecimal coatSizeEditFrontLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과값 앞기장")
    private BigDecimal coatSizeResultFrontLength;
}
