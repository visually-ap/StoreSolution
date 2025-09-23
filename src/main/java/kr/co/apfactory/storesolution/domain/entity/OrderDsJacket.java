package kr.co.apfactory.storesolution.domain.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_ds_jacket")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class OrderDsJacket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("시퀀스")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("주문 공통 시퀀스")
    private OrderCommon orderCommon;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("스타일")
    private CommonCodeChild jacketStyle;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("단추갯수")
    private CommonCodeChild jacketButtonCount;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("가슴포켓")
    private CommonCodeChild jacketChestPocket;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("어깨")
    private CommonCodeChild jacketShoulder;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("앞주머니")
    private CommonCodeChild jacketFrontPocket;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("패드(좌)")
    private CommonCodeChild jacketLeftPad;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("뒷트임")
    private CommonCodeChild jacketBackSlit;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("패드(우)")
    private CommonCodeChild jacketRightPad;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("소매단추")
    private CommonCodeChild jacketSleeveButton;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("라펠")
    private CommonCodeChild jacketLapel;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("소매")
    private CommonCodeChild jacketSleeve;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("턱시도")
    private CommonCodeChild jacketTuxedo;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("AMF(호시)")
    private CommonCodeChild jacketAmf;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("라펠Q")
    private CommonCodeChild jacketLapelQ;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("안감")
    private CommonCodeChild jacketLining;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("체크원단")
    private CommonCodeChild jacketCheckFabric;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("추가옵션")
    private CommonCodeChild jacketAddOption;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("굴신")
    private BigDecimal jacketSizeFrontRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("반신")
    private BigDecimal jacketSizeBackRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("견도")
    private BigDecimal jacketSizeShoulderSlope;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷고대")
    private BigDecimal jacketSizeBackNeckline;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("암홀")
    private BigDecimal jacketSizeArmHole;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷목점이동")
    private BigDecimal jacketSizeBackNeckPointMove;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("상박")
    private BigDecimal jacketSizeUpperArm;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("중박")
    private BigDecimal jacketSizeMiddleArm;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("하박")
    private BigDecimal jacketSizeLowerArm;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("앞기장")
    private BigDecimal jacketSizeFrontLength;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷중심이동")
    private BigDecimal jacketSizeBackCenterMove;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷중심줄임")
    private BigDecimal jacketSizeBackCenterShorten;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("첫단추")
    private BigDecimal jacketSizeFirstButton;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("목둘레")
    private BigDecimal jacketSizeNeckRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("도련선")
    private BigDecimal jacketSizeHemLine;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("우찌")
    private BigDecimal jacketSizeChestLine;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("패턴")
    private Pattern jacketPattern;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("게이지복")
    private PatternSize jacketGauge;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 상동")
    private BigDecimal jacketSizeRealInchChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상동")
    private BigDecimal jacketSizeRealCentiChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상동")
    private BigDecimal jacketSizeEditChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 상동")
    private BigDecimal jacketSizeResultChest;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 상앞품")
    private BigDecimal jacketSizeRealInchChestWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상앞품")
    private BigDecimal jacketSizeRealCentiChestWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상앞품")
    private BigDecimal jacketSizeEditChestWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 상앞품")
    private BigDecimal jacketSizeResultChestWidth;



    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 상사이바")
    private BigDecimal jacketSizeRealInchUpperSideBody;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상사이바")
    private BigDecimal jacketSizeRealCentiUpperSideBody;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상사이바")
    private BigDecimal jacketSizeEditUpperSideBody;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 상사이바")
    private BigDecimal jacketSizeResultUpperSideBody;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 상뒷품")
    private BigDecimal jacketSizeRealInchBackWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상뒷품")
    private BigDecimal jacketSizeRealCentiBackWidth;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상뒷품")
    private BigDecimal jacketSizeEditBackWidth;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 상뒷품")
    private BigDecimal jacketSizeResultBackWidth;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 중동")
    private BigDecimal jacketSizeRealInchWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중동")
    private BigDecimal jacketSizeRealCentiWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중동")
    private BigDecimal jacketSizeEditWaist;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 중동")
    private BigDecimal jacketSizeResultWaist;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 중앞품")
    private BigDecimal jacketSizeRealInchWaistFront;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중앞품")
    private BigDecimal jacketSizeRealCentiWaistFront;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중앞품")
    private BigDecimal jacketSizeEditWaistFront;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 중앞품")
    private BigDecimal jacketSizeResultWaistFront;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 중사이바")
    private BigDecimal jacketSizeRealInchMiddleSideBody;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중사이바")
    private BigDecimal jacketSizeRealCentiMiddleSideBody;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중사이바")
    private BigDecimal jacketSizeEditMiddleSideBody;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 중사이바")
    private BigDecimal jacketSizeResultMiddleSideBody;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 중뒷품")
    private BigDecimal jacketSizeRealInchWaistBack;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중뒷품")
    private BigDecimal jacketSizeRealCentiWaistBack;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중뒷품")
    private BigDecimal jacketSizeEditWaistBack;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 중뒷품")
    private BigDecimal jacketSizeResultWaistBack;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 어깨")
    private BigDecimal jacketSizeRealInchShoulder;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 어깨")
    private BigDecimal jacketSizeRealCentiShoulder;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 어깨")
    private BigDecimal jacketSizeEditShoulder;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 어깨")
    private BigDecimal jacketSizeResultShoulder;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 소매(좌)")
    private BigDecimal jacketSizeRealInchLeftSleeve;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 소매(좌)")
    private BigDecimal jacketSizeRealCentiLeftSleeve;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 소매(좌)")
    private BigDecimal jacketSizeEditLeftSleeve;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 소매(좌)")
    private BigDecimal jacketSizeResultLeftSleeve;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 소매(우)")
    private BigDecimal jacketSizeRealInchRightSleeve;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 소매(우)")
    private BigDecimal jacketSizeRealCentiRightSleeve;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 소매(우)")
    private BigDecimal jacketSizeEditRightSleeve;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 소매(우)")
    private BigDecimal jacketSizeResultRightSleeve;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 뒷기장")
    private BigDecimal jacketSizeRealInchBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 뒷기장")
    private BigDecimal jacketSizeRealCentiBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 뒷기장")
    private BigDecimal jacketSizeEditBackLength;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 뒷기장")
    private BigDecimal jacketSizeResultBackLength;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 앞기장")
    private BigDecimal jacketSizeRealInchFrontLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 앞기장")
    private BigDecimal jacketSizeRealCentiFrontLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 앞기장")
    private BigDecimal jacketSizeEditFrontLength;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("결과 앞기장")
    private BigDecimal jacketSizeResultFrontLength;
}
