package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqDesignSaveDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqFabricSaveDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqSizeSaveDTO;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_counseling_jacket")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CounselingJacket {
    public static Long CAVAN_JACKET = 33L;
    public static Long CAVAN_STYLE = 29L;
    public static Long CAVAN_BUTTON_COUNT = 191L;
    public static Long CAVAN_FRONT_POCKET = 177L;
    public static Long CAVAN_LAPEL = 139L;
    public static Long CAVAN_AMF = 105L;
    public static Long CAVAN_LAPELQ = 97L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("카운셀링 자켓 디자인 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("카운셀링 공통 아이디")
    private CounselingCommon counselingCommon;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("패턴")
    private Pattern jacketPattern;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("게이지복")
    private PatternSize jacketGauge;

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



    // 사이즈
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
    @Comment("실측 인치 상앞품")
    private BigDecimal jacketSizeRealInchChestWidth;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상앞품")
    private BigDecimal jacketSizeRealCentiChestWidth;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상앞품")
    private BigDecimal jacketSizeEditChestWidth;

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
    @Comment("실측 인치 상뒷품")
    private BigDecimal jacketSizeRealInchBackWidth;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상뒷품")
    private BigDecimal jacketSizeRealCentiBackWidth;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상뒷품")
    private BigDecimal jacketSizeEditBackWidth;

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
    @Comment("실측 인치 중앞품")
    private BigDecimal jacketSizeRealInchWaistFront;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중앞품")
    private BigDecimal jacketSizeRealCentiWaistFront;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중앞품")
    private BigDecimal jacketSizeEditWaistFront;

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
    @Comment("실측 인치 중뒷품")
    private BigDecimal jacketSizeRealInchWaistBack;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중뒷품")
    private BigDecimal jacketSizeRealCentiWaistBack;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중뒷품")
    private BigDecimal jacketSizeEditWaistBack;

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
    @Comment("실측 인치 소매(좌)")
    private BigDecimal jacketSizeRealInchLeftSleeve;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 소매(좌)")
    private BigDecimal jacketSizeRealCentiLeftSleeve;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 소매(좌)")
    private BigDecimal jacketSizeEditLeftSleeve;

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
    @Comment("실측 인치 뒷기장")
    private BigDecimal jacketSizeRealInchBackLength;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 뒷기장")
    private BigDecimal jacketSizeRealCentiBackLength;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 뒷기장")
    private BigDecimal jacketSizeEditBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 앞기장")
    private BigDecimal jacketSizeRealInchFrontLength;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 앞기장")
    private BigDecimal jacketSizeRealCentiFrontLength;
    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 앞기장")
    private BigDecimal jacketSizeEditFrontLength;

    // 체형
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
    @Comment("뒷목점이동")
    private BigDecimal jacketSizeBackNeckPointMove;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("목둘레")
    private BigDecimal jacketSizeNeckRound;

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
    @Comment("앞기장 추가")
    private BigDecimal jacketSizeFrontLength;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("첫단추")
    private BigDecimal jacketSizeFirstButton;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("도련선")
    private BigDecimal jacketSizeHemLine;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷중심이동")
    private BigDecimal jacketSizeBackCenterMove;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷중심줄임")
    private BigDecimal jacketSizeBackCenterShorten;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("암홀수정")
    private BigDecimal jacketSizeArmHole;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("우찌")
    private BigDecimal jacketSizeChestLine;

    public void updateDesignData(ReqDesignSaveDTO dto) {
        this.jacketStyle =  CommonCodeChild.builder().id(dto.getJacketStyle()).build();
        this.jacketButtonCount = CommonCodeChild.builder().id(dto.getJacketButtonCount()).build();
        this.jacketChestPocket = CommonCodeChild.builder().id(dto.getJacketChestPocket()).build();
        this.jacketShoulder = CommonCodeChild.builder().id(dto.getJacketShoulder()).build();
        this.jacketFrontPocket = CommonCodeChild.builder().id(dto.getJacketFrontPocket()).build();
        this.jacketRightPad = CommonCodeChild.builder().id(dto.getJacketRightPad()).build();
        this.jacketLeftPad = CommonCodeChild.builder().id(dto.getJacketLeftPad()).build();
        this.jacketBackSlit = CommonCodeChild.builder().id(dto.getJacketBackSlit()).build();
        this.jacketSleeveButton = CommonCodeChild.builder().id(dto.getJacketSleeveButton()).build();
        this.jacketLapel = CommonCodeChild.builder().id(dto.getJacketLapel()).build();
        this.jacketSleeve = CommonCodeChild.builder().id(dto.getJacketSleeve()).build();
        this.jacketTuxedo = CommonCodeChild.builder().id(dto.getJacketTuxedo()).build();
        this.jacketAmf = CommonCodeChild.builder().id(dto.getJacketAmf()).build();
        this.jacketLapelQ = CommonCodeChild.builder().id(dto.getJacketLapelQ()).build();
        this.jacketLining = CommonCodeChild.builder().id(dto.getJacketLining()).build();
        this.jacketCheckFabric = CommonCodeChild.builder().id(dto.getJacketCheckFabric()).build();
        this.jacketAddOption = CommonCodeChild.builder().id(dto.getJacketAddOption()).build();
    }

    public void initDesignData() {
        this.jacketPattern = null;
        this.jacketStyle =  null;
        this.jacketButtonCount = null;
        this.jacketChestPocket = null;
        this.jacketShoulder = null;
        this.jacketFrontPocket = null;
        this.jacketRightPad = null;
        this.jacketLeftPad = null;
        this.jacketBackSlit = null;
        this.jacketSleeveButton = null;
        this.jacketLapel = null;
        this.jacketSleeve = null;
        this.jacketTuxedo = null;
        this.jacketAmf = null;
        this.jacketLapelQ = null;
        this.jacketLining = null;
        this.jacketCheckFabric = null;
        this.jacketAddOption = null;
    }

    public void updateSizeData(ReqSizeSaveDTO dto) {
        this.jacketPattern = dto.getJacketPattern() == null ? null : Pattern.builder().id(dto.getJacketPattern()).build();
        this.jacketGauge = dto.getJacketGauge() == null ? null : PatternSize.builder().id(dto.getJacketGauge()).build();
        this.jacketSizeRealInchChest = dto.getJacketSizeRealInchChest() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchChest();
        this.jacketSizeRealCentiChest = dto.getJacketSizeRealCentiChest() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiChest();
        this.jacketSizeEditChest = dto.getJacketSizeEditChest() == null ? BigDecimal.ZERO : dto.getJacketSizeEditChest();

        this.jacketSizeRealInchChestWidth = dto.getJacketSizeRealInchChestWidth() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchChestWidth();
        this.jacketSizeRealCentiChestWidth = dto.getJacketSizeRealCentiChestWidth() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiChestWidth();
        this.jacketSizeEditChestWidth = dto.getJacketSizeEditChestWidth() == null ? BigDecimal.ZERO : dto.getJacketSizeEditChestWidth();

        this.jacketSizeRealInchUpperSideBody = dto.getJacketSizeRealInchUpperSideBody() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchUpperSideBody();
        this.jacketSizeRealCentiUpperSideBody = dto.getJacketSizeRealCentiUpperSideBody() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiUpperSideBody();
        this.jacketSizeEditUpperSideBody = dto.getJacketSizeEditUpperSideBody() == null ? BigDecimal.ZERO : dto.getJacketSizeEditUpperSideBody();

        this.jacketSizeRealInchBackWidth = dto.getJacketSizeRealInchBackWidth() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchBackWidth();
        this.jacketSizeRealCentiBackWidth = dto.getJacketSizeRealCentiBackWidth() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiBackWidth();
        this.jacketSizeEditBackWidth = dto.getJacketSizeEditBackWidth() == null ? BigDecimal.ZERO : dto.getJacketSizeEditBackWidth();

        this.jacketSizeRealInchWaist = dto.getJacketSizeRealInchWaist() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchWaist();
        this.jacketSizeRealCentiWaist = dto.getJacketSizeRealCentiWaist() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiWaist();
        this.jacketSizeEditWaist = dto.getJacketSizeEditWaist() == null ? BigDecimal.ZERO : dto.getJacketSizeEditWaist();

        this.jacketSizeRealInchWaistFront = dto.getJacketSizeRealInchWaistFront() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchWaistFront();
        this.jacketSizeRealCentiWaistFront = dto.getJacketSizeRealCentiWaistFront() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiWaistFront();
        this.jacketSizeEditWaistFront = dto.getJacketSizeEditWaistFront() == null ? BigDecimal.ZERO : dto.getJacketSizeEditWaistFront();

        this.jacketSizeRealInchMiddleSideBody = dto.getJacketSizeRealInchMiddleSideBody() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchMiddleSideBody();
        this.jacketSizeRealCentiMiddleSideBody = dto.getJacketSizeRealCentiMiddleSideBody() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiMiddleSideBody();
        this.jacketSizeEditMiddleSideBody = dto.getJacketSizeEditMiddleSideBody() == null ? BigDecimal.ZERO : dto.getJacketSizeEditMiddleSideBody();

        this.jacketSizeRealInchWaistBack = dto.getJacketSizeRealInchWaistBack() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchWaistBack();
        this.jacketSizeRealCentiWaistBack = dto.getJacketSizeRealCentiWaistBack() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiWaistBack();
        this.jacketSizeEditWaistBack = dto.getJacketSizeEditWaistBack() == null ? BigDecimal.ZERO : dto.getJacketSizeEditWaistBack();

        this.jacketSizeRealInchShoulder = dto.getJacketSizeRealInchShoulder() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchShoulder();
        this.jacketSizeRealCentiShoulder = dto.getJacketSizeRealCentiShoulder() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiShoulder();
        this.jacketSizeEditShoulder = dto.getJacketSizeEditShoulder() == null ? BigDecimal.ZERO : dto.getJacketSizeEditShoulder();

        this.jacketSizeRealInchLeftSleeve = dto.getJacketSizeRealInchLeftSleeve() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchLeftSleeve();
        this.jacketSizeRealCentiLeftSleeve = dto.getJacketSizeRealCentiLeftSleeve() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiLeftSleeve();
        this.jacketSizeEditLeftSleeve = dto.getJacketSizeEditLeftSleeve() == null ? BigDecimal.ZERO : dto.getJacketSizeEditLeftSleeve();

        this.jacketSizeRealInchRightSleeve = dto.getJacketSizeRealInchRightSleeve() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchRightSleeve();
        this.jacketSizeRealCentiRightSleeve = dto.getJacketSizeRealCentiRightSleeve() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiRightSleeve();
        this.jacketSizeEditRightSleeve = dto.getJacketSizeEditRightSleeve() == null ? BigDecimal.ZERO : dto.getJacketSizeEditRightSleeve();

        this.jacketSizeRealInchBackLength = dto.getJacketSizeRealInchBackLength() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchBackLength();
        this.jacketSizeRealCentiBackLength = dto.getJacketSizeRealCentiBackLength() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiBackLength();
        this.jacketSizeEditBackLength = dto.getJacketSizeEditBackLength() == null ? BigDecimal.ZERO : dto.getJacketSizeEditBackLength();

        this.jacketSizeRealInchFrontLength = dto.getJacketSizeRealInchFrontLength() == null ? BigDecimal.ZERO : dto.getJacketSizeRealInchFrontLength();
        this.jacketSizeRealCentiFrontLength = dto.getJacketSizeRealCentiFrontLength() == null ? BigDecimal.ZERO : dto.getJacketSizeRealCentiFrontLength();
        this.jacketSizeEditFrontLength = dto.getJacketSizeEditFrontLength() == null ? BigDecimal.ZERO : dto.getJacketSizeEditFrontLength();

        this.jacketSizeFrontRound = dto.getJacketSizeFrontRound() == null ? BigDecimal.ZERO : dto.getJacketSizeFrontRound();
        this.jacketSizeBackRound = dto.getJacketSizeBackRound() == null ? BigDecimal.ZERO : dto.getJacketSizeBackRound();
        this.jacketSizeShoulderSlope = dto.getJacketSizeShoulderSlope() == null ? BigDecimal.ZERO : dto.getJacketSizeShoulderSlope();
        this.jacketSizeBackNeckline = dto.getJacketSizeBackNeckline() == null ? BigDecimal.ZERO : dto.getJacketSizeBackNeckline();
        this.jacketSizeBackNeckPointMove = dto.getJacketSizeBackNeckPointMove() == null ? BigDecimal.ZERO : dto.getJacketSizeBackNeckPointMove();
        this.jacketSizeNeckRound = dto.getJacketSizeNeckRound() == null ? BigDecimal.ZERO : dto.getJacketSizeNeckRound();
        this.jacketSizeUpperArm = dto.getJacketSizeUpperArm() == null ? BigDecimal.ZERO : dto.getJacketSizeUpperArm();
        this.jacketSizeMiddleArm = dto.getJacketSizeMiddleArm() == null ? BigDecimal.ZERO : dto.getJacketSizeMiddleArm();
        this.jacketSizeLowerArm = dto.getJacketSizeLowerArm() == null ? BigDecimal.ZERO : dto.getJacketSizeLowerArm();
        this.jacketSizeFrontLength = dto.getJacketSizeFrontLength() == null ? BigDecimal.ZERO : dto.getJacketSizeFrontLength();
        this.jacketSizeFirstButton = dto.getJacketSizeFirstButton() == null ? BigDecimal.ZERO : dto.getJacketSizeFirstButton();
        this.jacketSizeHemLine = dto.getJacketSizeHemLine() == null ? BigDecimal.ZERO : dto.getJacketSizeHemLine();
        this.jacketSizeBackCenterMove = dto.getJacketSizeBackCenterMove() == null ? BigDecimal.ZERO : dto.getJacketSizeBackCenterMove();
        this.jacketSizeBackCenterShorten = dto.getJacketSizeBackCenterShorten() == null ? BigDecimal.ZERO : dto.getJacketSizeBackCenterShorten();
        this.jacketSizeArmHole = dto.getJacketSizeArmHole() == null ? BigDecimal.ZERO : dto.getJacketSizeArmHole();
        this.jacketSizeChestLine = dto.getJacketSizeChestLine() == null ? BigDecimal.ZERO : dto.getJacketSizeChestLine();
    }

    public void initSizeData() {
        this.jacketSizeRealInchChest = BigDecimal.ZERO;
        this.jacketSizeRealCentiChest = BigDecimal.ZERO;
        this.jacketSizeEditChest = BigDecimal.ZERO;

        this.jacketSizeRealInchChestWidth = BigDecimal.ZERO;
        this.jacketSizeRealCentiChestWidth = BigDecimal.ZERO;
        this.jacketSizeEditChestWidth = BigDecimal.ZERO;

        this.jacketSizeRealInchUpperSideBody = BigDecimal.ZERO;
        this.jacketSizeRealCentiUpperSideBody = BigDecimal.ZERO;
        this.jacketSizeEditUpperSideBody = BigDecimal.ZERO;

        this.jacketSizeRealInchBackWidth = BigDecimal.ZERO;
        this.jacketSizeRealCentiBackWidth = BigDecimal.ZERO;
        this.jacketSizeEditBackWidth = BigDecimal.ZERO;

        this.jacketSizeRealInchWaist = BigDecimal.ZERO;
        this.jacketSizeRealCentiWaist = BigDecimal.ZERO;
        this.jacketSizeEditWaist = BigDecimal.ZERO;

        this.jacketSizeRealInchWaistFront = BigDecimal.ZERO;
        this.jacketSizeRealCentiWaistFront = BigDecimal.ZERO;
        this.jacketSizeEditWaistFront = BigDecimal.ZERO;

        this.jacketSizeRealInchMiddleSideBody = BigDecimal.ZERO;
        this.jacketSizeRealCentiMiddleSideBody = BigDecimal.ZERO;
        this.jacketSizeEditMiddleSideBody = BigDecimal.ZERO;

        this.jacketSizeRealInchWaistBack = BigDecimal.ZERO;
        this.jacketSizeRealCentiWaistBack = BigDecimal.ZERO;
        this.jacketSizeEditWaistBack = BigDecimal.ZERO;

        this.jacketSizeRealInchShoulder = BigDecimal.ZERO;
        this.jacketSizeRealCentiShoulder = BigDecimal.ZERO;
        this.jacketSizeEditShoulder = BigDecimal.ZERO;

        this.jacketSizeRealInchLeftSleeve = BigDecimal.ZERO;
        this.jacketSizeRealCentiLeftSleeve = BigDecimal.ZERO;
        this.jacketSizeEditLeftSleeve = BigDecimal.ZERO;

        this.jacketSizeRealInchRightSleeve = BigDecimal.ZERO;
        this.jacketSizeRealCentiRightSleeve = BigDecimal.ZERO;
        this.jacketSizeEditRightSleeve = BigDecimal.ZERO;

        this.jacketSizeRealInchBackLength = BigDecimal.ZERO;
        this.jacketSizeRealCentiBackLength = BigDecimal.ZERO;
        this.jacketSizeEditBackLength = BigDecimal.ZERO;

        this.jacketSizeRealInchFrontLength = BigDecimal.ZERO;
        this.jacketSizeRealCentiFrontLength = BigDecimal.ZERO;
        this.jacketSizeEditFrontLength = BigDecimal.ZERO;
    }

    public void setCavanDesignOption() {
        this.jacketStyle =  CommonCodeChild.builder().id(CAVAN_STYLE).build();
        this.jacketButtonCount = CommonCodeChild.builder().id(CAVAN_BUTTON_COUNT).build();
        this.jacketFrontPocket = CommonCodeChild.builder().id(CAVAN_FRONT_POCKET).build();
        this.jacketLapel = CommonCodeChild.builder().id(CAVAN_LAPEL).build();
        this.jacketAmf = CommonCodeChild.builder().id(CAVAN_AMF).build();
        this.jacketLapelQ = CommonCodeChild.builder().id(CAVAN_LAPELQ).build();
    }

    public void initDesignOptionFromCavan() {
        this.jacketStyle =  CommonCodeChild.builder().id(27L).build();
        this.jacketButtonCount = CommonCodeChild.builder().id(30L).build();
        this.jacketFrontPocket = CommonCodeChild.builder().id(166L).build();
        this.jacketLapel = CommonCodeChild.builder().id(472L).build();
        this.jacketAmf = CommonCodeChild.builder().id(39L).build();
        this.jacketLapelQ = CommonCodeChild.builder().id(37L).build();
    }
}
