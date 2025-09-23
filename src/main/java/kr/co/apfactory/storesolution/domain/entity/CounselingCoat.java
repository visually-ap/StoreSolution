package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqDesignSaveDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqSizeSaveDTO;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_counseling_coat")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CounselingCoat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("카운셀링 하의 디자인 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("카운셀링 공통 아이디")
    private CounselingCommon counselingCommon;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("패턴")
    private Pattern coatPattern;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("게이지복")
    private PatternSize coatGauge;

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

    // 사이즈
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
    @Comment("실측 인치 상앞품")
    private BigDecimal coatSizeRealInchChestWidth;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상앞품")
    private BigDecimal coatSizeRealCentiChestWidth;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상앞품")
    private BigDecimal coatSizeEditChestWidth;

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
    @Comment("실측 인치 상뒷품")
    private BigDecimal coatSizeRealInchBackWidth;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상뒷품")
    private BigDecimal coatSizeRealCentiBackWidth;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상뒷품")
    private BigDecimal coatSizeEditBackWidth;

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
    @Comment("실측 인치 중앞품")
    private BigDecimal coatSizeRealInchWaistFront;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중앞품")
    private BigDecimal coatSizeRealCentiWaistFront;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중앞품")
    private BigDecimal coatSizeEditWaistFront;

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
    @Comment("실측 인치 중뒷품")
    private BigDecimal coatSizeRealInchWaistBack;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중뒷품")
    private BigDecimal coatSizeRealCentiWaistBack;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중뒷품")
    private BigDecimal coatSizeEditWaistBack;

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
    @Comment("실측 인치 소매(좌)")
    private BigDecimal coatSizeRealInchLeftSleeve;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 소매(좌)")
    private BigDecimal coatSizeRealCentiLeftSleeve;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 소매(좌)")
    private BigDecimal coatSizeEditLeftSleeve;

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
    @Comment("실측 인치 뒷기장")
    private BigDecimal coatSizeRealInchBackLength;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 뒷기장")
    private BigDecimal coatSizeRealCentiBackLength;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 뒷기장")
    private BigDecimal coatSizeEditBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 앞기장")
    private BigDecimal coatSizeRealInchFrontLength;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 앞기장")
    private BigDecimal coatSizeRealCentiFrontLength;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 앞기장")
    private BigDecimal coatSizeEditFrontLength;

    // 체형
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
    @Comment("뒷목점이동")
    private BigDecimal coatSizeBackNeckPointMove;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("목둘레")
    private BigDecimal coatSizeNeckRound;

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
    @Comment("앞기장 추가")
    private BigDecimal coatSizeFrontLength;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("첫단추")
    private BigDecimal coatSizeFirstButton;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("도련선")
    private BigDecimal coatSizeHemLine;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷중심이동")
    private BigDecimal coatSizeBackCenterMove;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷중심줄임")
    private BigDecimal coatSizeBackCenterShorten;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("암홀수정")
    private BigDecimal coatSizeArmHole;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("우찌")
    private BigDecimal coatSizeChestLine;

    public void updateDesignData(ReqDesignSaveDTO dto) {
        this.coatStyle =  CommonCodeChild.builder().id(dto.getCoatStyle()).build();
        this.coatButtonCount = CommonCodeChild.builder().id(dto.getCoatButtonCount()).build();
        this.coatChestPocket = CommonCodeChild.builder().id(dto.getCoatChestPocket()).build();
        this.coatShoulder = CommonCodeChild.builder().id(dto.getCoatShoulder()).build();
        this.coatFrontPocket = CommonCodeChild.builder().id(dto.getCoatFrontPocket()).build();
        this.coatRightPad = CommonCodeChild.builder().id(dto.getCoatRightPad()).build();
        this.coatLeftPad = CommonCodeChild.builder().id(dto.getCoatLeftPad()).build();
        this.coatBackSlit = CommonCodeChild.builder().id(dto.getCoatBackSlit()).build();
        this.coatSleeveButton = CommonCodeChild.builder().id(dto.getCoatSleeveButton()).build();
        this.coatLapel = CommonCodeChild.builder().id(dto.getCoatLapel()).build();
        this.coatSleeve = CommonCodeChild.builder().id(dto.getCoatSleeve()).build();
        this.coatAmf = CommonCodeChild.builder().id(dto.getCoatAmf()).build();
        this.coatLapelQ = CommonCodeChild.builder().id(dto.getCoatLapelQ()).build();
        this.coatLining = CommonCodeChild.builder().id(dto.getCoatLining()).build();
        this.coatCheckFabric = CommonCodeChild.builder().id(dto.getCoatCheckFabric()).build();
        this.coatAddOption1 = CommonCodeChild.builder().id(dto.getCoatAddOption1()).build();
        this.coatAddOption2 = CommonCodeChild.builder().id(dto.getCoatAddOption2()).build();
    }

    public void initDesignData() {
        this.coatPattern = null;
        this.coatStyle =  null;
        this.coatButtonCount = null;
        this.coatChestPocket = null;
        this.coatShoulder = null;
        this.coatFrontPocket = null;
        this.coatRightPad = null;
        this.coatLeftPad = null;
        this.coatBackSlit = null;
        this.coatSleeveButton = null;
        this.coatLapel = null;
        this.coatSleeve = null;
        this.coatAmf = null;
        this.coatLapelQ = null;
        this.coatLining = null;
        this.coatCheckFabric = null;
        this.coatAddOption1 = null;
        this.coatAddOption2 = null;
    }

    public void updateSizeData(ReqSizeSaveDTO dto) {
        this.coatPattern = dto.getCoatPattern() == null ? null : Pattern.builder().id(dto.getCoatPattern()).build();
        this.coatGauge = dto.getCoatGauge() == null ? null : PatternSize.builder().id(dto.getCoatGauge()).build();
        this.coatSizeRealInchChest = dto.getCoatSizeRealInchChest() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchChest();
        this.coatSizeRealCentiChest = dto.getCoatSizeRealCentiChest() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiChest();
        this.coatSizeEditChest = dto.getCoatSizeEditChest() == null ? BigDecimal.ZERO : dto.getCoatSizeEditChest();

        this.coatSizeRealInchChestWidth = dto.getCoatSizeRealInchChestWidth() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchChestWidth();
        this.coatSizeRealCentiChestWidth = dto.getCoatSizeRealCentiChestWidth() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiChestWidth();
        this.coatSizeEditChestWidth = dto.getCoatSizeEditChestWidth() == null ? BigDecimal.ZERO : dto.getCoatSizeEditChestWidth();

        this.coatSizeRealInchUpperSideBody = dto.getCoatSizeRealInchUpperSideBody() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchUpperSideBody();
        this.coatSizeRealCentiUpperSideBody = dto.getCoatSizeRealCentiUpperSideBody() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiUpperSideBody();
        this.coatSizeEditUpperSideBody = dto.getCoatSizeEditUpperSideBody() == null ? BigDecimal.ZERO : dto.getCoatSizeEditUpperSideBody();

        this.coatSizeRealInchBackWidth = dto.getCoatSizeRealInchBackWidth() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchBackWidth();
        this.coatSizeRealCentiBackWidth = dto.getCoatSizeRealCentiBackWidth() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiBackWidth();
        this.coatSizeEditBackWidth = dto.getCoatSizeEditBackWidth() == null ? BigDecimal.ZERO : dto.getCoatSizeEditBackWidth();

        this.coatSizeRealInchWaist = dto.getCoatSizeRealInchWaist() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchWaist();
        this.coatSizeRealCentiWaist = dto.getCoatSizeRealCentiWaist() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiWaist();
        this.coatSizeEditWaist = dto.getCoatSizeEditWaist() == null ? BigDecimal.ZERO : dto.getCoatSizeEditWaist();

        this.coatSizeRealInchWaistFront = dto.getCoatSizeRealInchWaistFront() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchWaistFront();
        this.coatSizeRealCentiWaistFront = dto.getCoatSizeRealCentiWaistFront() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiWaistFront();
        this.coatSizeEditWaistFront = dto.getCoatSizeEditWaistFront() == null ? BigDecimal.ZERO : dto.getCoatSizeEditWaistFront();

        this.coatSizeRealInchMiddleSideBody = dto.getCoatSizeRealInchMiddleSideBody() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchMiddleSideBody();
        this.coatSizeRealCentiMiddleSideBody = dto.getCoatSizeRealCentiMiddleSideBody() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiMiddleSideBody();
        this.coatSizeEditMiddleSideBody = dto.getCoatSizeEditMiddleSideBody() == null ? BigDecimal.ZERO : dto.getCoatSizeEditMiddleSideBody();

        this.coatSizeRealInchWaistBack = dto.getCoatSizeRealInchWaistBack() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchWaistBack();
        this.coatSizeRealCentiWaistBack = dto.getCoatSizeRealCentiWaistBack() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiWaistBack();
        this.coatSizeEditWaistBack = dto.getCoatSizeEditWaistBack() == null ? BigDecimal.ZERO : dto.getCoatSizeEditWaistBack();

        this.coatSizeRealInchShoulder = dto.getCoatSizeRealInchShoulder() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchShoulder();
        this.coatSizeRealCentiShoulder = dto.getCoatSizeRealCentiShoulder() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiShoulder();
        this.coatSizeEditShoulder = dto.getCoatSizeEditShoulder() == null ? BigDecimal.ZERO : dto.getCoatSizeEditShoulder();

        this.coatSizeRealInchLeftSleeve = dto.getCoatSizeRealInchLeftSleeve() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchLeftSleeve();
        this.coatSizeRealCentiLeftSleeve = dto.getCoatSizeRealCentiLeftSleeve() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiLeftSleeve();
        this.coatSizeEditLeftSleeve = dto.getCoatSizeEditLeftSleeve() == null ? BigDecimal.ZERO : dto.getCoatSizeEditLeftSleeve();

        this.coatSizeRealInchRightSleeve = dto.getCoatSizeRealInchRightSleeve() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchRightSleeve();
        this.coatSizeRealCentiRightSleeve = dto.getCoatSizeRealCentiRightSleeve() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiRightSleeve();
        this.coatSizeEditRightSleeve = dto.getCoatSizeEditRightSleeve() == null ? BigDecimal.ZERO : dto.getCoatSizeEditRightSleeve();

        this.coatSizeRealInchBackLength = dto.getCoatSizeRealInchBackLength() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchBackLength();
        this.coatSizeRealCentiBackLength = dto.getCoatSizeRealCentiBackLength() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiBackLength();
        this.coatSizeEditBackLength = dto.getCoatSizeEditBackLength() == null ? BigDecimal.ZERO : dto.getCoatSizeEditBackLength();

        this.coatSizeRealInchFrontLength = dto.getCoatSizeRealInchFrontLength() == null ? BigDecimal.ZERO : dto.getCoatSizeRealInchFrontLength();
        this.coatSizeRealCentiFrontLength = dto.getCoatSizeRealCentiFrontLength() == null ? BigDecimal.ZERO : dto.getCoatSizeRealCentiFrontLength();
        this.coatSizeEditFrontLength = dto.getCoatSizeEditFrontLength() == null ? BigDecimal.ZERO : dto.getCoatSizeEditFrontLength();

        this.coatSizeFrontRound = dto.getCoatSizeFrontRound() == null ? BigDecimal.ZERO : dto.getCoatSizeFrontRound();
        this.coatSizeBackRound = dto.getCoatSizeBackRound() == null ? BigDecimal.ZERO : dto.getCoatSizeBackRound();
        this.coatSizeShoulderSlope = dto.getCoatSizeShoulderSlope() == null ? BigDecimal.ZERO : dto.getCoatSizeShoulderSlope();
        this.coatSizeBackNeckline = dto.getCoatSizeBackNeckline() == null ? BigDecimal.ZERO : dto.getCoatSizeBackNeckline();
        this.coatSizeBackNeckPointMove = dto.getCoatSizeBackNeckPointMove() == null ? BigDecimal.ZERO : dto.getCoatSizeBackNeckPointMove();
        this.coatSizeNeckRound = dto.getCoatSizeNeckRound() == null ? BigDecimal.ZERO : dto.getCoatSizeNeckRound();
        this.coatSizeUpperArm = dto.getCoatSizeUpperArm() == null ? BigDecimal.ZERO : dto.getCoatSizeUpperArm();
        this.coatSizeMiddleArm = dto.getCoatSizeMiddleArm() == null ? BigDecimal.ZERO : dto.getCoatSizeMiddleArm();
        this.coatSizeLowerArm = dto.getCoatSizeLowerArm() == null ? BigDecimal.ZERO : dto.getCoatSizeLowerArm();
        this.coatSizeFrontLength = dto.getCoatSizeFrontLength() == null ? BigDecimal.ZERO : dto.getCoatSizeFrontLength();
        this.coatSizeFirstButton = dto.getCoatSizeFirstButton() == null ? BigDecimal.ZERO : dto.getCoatSizeFirstButton();
        this.coatSizeHemLine = dto.getCoatSizeHemLine() == null ? BigDecimal.ZERO : dto.getCoatSizeHemLine();
        this.coatSizeBackCenterMove = dto.getCoatSizeBackCenterMove() == null ? BigDecimal.ZERO : dto.getCoatSizeBackCenterMove();
        this.coatSizeBackCenterShorten = dto.getCoatSizeBackCenterShorten() == null ? BigDecimal.ZERO : dto.getCoatSizeBackCenterShorten();
        this.coatSizeArmHole = dto.getCoatSizeArmHole() == null ? BigDecimal.ZERO : dto.getCoatSizeArmHole();
        this.coatSizeChestLine = dto.getCoatSizeChestLine() == null ? BigDecimal.ZERO : dto.getCoatSizeChestLine();
    }
}
