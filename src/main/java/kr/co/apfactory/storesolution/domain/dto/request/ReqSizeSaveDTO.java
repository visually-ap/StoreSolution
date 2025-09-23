package kr.co.apfactory.storesolution.domain.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReqSizeSaveDTO {
    private Long reservationId;

    // 사이즈 시작
    private Long jacketPattern;
    private Long jacketGauge;

    private BigDecimal jacketSizeFrontRound;
    private BigDecimal jacketSizeBackRound;
    private BigDecimal jacketSizeShoulderSlope;
    private BigDecimal jacketSizeBackNeckline;
    private BigDecimal jacketSizeArmHole;
    private BigDecimal jacketSizeBackNeckPointMove;
    private BigDecimal jacketSizeUpperArm;
    private BigDecimal jacketSizeMiddleArm;
    private BigDecimal jacketSizeLowerArm;
    private BigDecimal jacketSizeFrontLength;
    private BigDecimal jacketSizeBackCenterMove;
    private BigDecimal jacketSizeBackCenterShorten;
    private BigDecimal jacketSizeFirstButton;
    private BigDecimal jacketSizeNeckRound;
    private BigDecimal jacketSizeHemLine;
    private BigDecimal jacketSizeChestLine;

    private BigDecimal jacketSizeRealInchChest;
    private BigDecimal jacketSizeRealCentiChest;
    private BigDecimal jacketSizeGaugeChest;
    private BigDecimal jacketSizeEditChest;
    private BigDecimal jacketSizeResultChest;

    private BigDecimal jacketSizeRealInchChestWidth;
    private BigDecimal jacketSizeRealCentiChestWidth;
    private BigDecimal jacketSizeGaugeChestWidth;
    private BigDecimal jacketSizeEditChestWidth;
    private BigDecimal jacketSizeResultChestWidth;

    private BigDecimal jacketSizeRealInchUpperSideBody;
    private BigDecimal jacketSizeRealCentiUpperSideBody;
    private BigDecimal jacketSizeGaugeUpperSideBody;
    private BigDecimal jacketSizeEditUpperSideBody;
    private BigDecimal jacketSizeResultUpperSideBody;

    private BigDecimal jacketSizeRealInchBackWidth;
    private BigDecimal jacketSizeRealCentiBackWidth;
    private BigDecimal jacketSizeGaugeBackWidth;
    private BigDecimal jacketSizeEditBackWidth;
    private BigDecimal jacketSizeResultBackWidth;

    private BigDecimal jacketSizeRealInchWaist;
    private BigDecimal jacketSizeRealCentiWaist;
    private BigDecimal jacketSizeGaugeWaist;
    private BigDecimal jacketSizeEditWaist;
    private BigDecimal jacketSizeResultWaist;

    private BigDecimal jacketSizeRealInchWaistFront;
    private BigDecimal jacketSizeRealCentiWaistFront;
    private BigDecimal jacketSizeGaugeWaistFront;
    private BigDecimal jacketSizeEditWaistFront;
    private BigDecimal jacketSizeResultWaistFront;

    private BigDecimal jacketSizeRealInchMiddleSideBody;
    private BigDecimal jacketSizeRealCentiMiddleSideBody;
    private BigDecimal jacketSizeGaugeMiddleSideBody;
    private BigDecimal jacketSizeEditMiddleSideBody;
    private BigDecimal jacketSizeResultMiddleSideBody;

    private BigDecimal jacketSizeRealInchWaistBack;
    private BigDecimal jacketSizeRealCentiWaistBack;
    private BigDecimal jacketSizeGaugeWaistBack;
    private BigDecimal jacketSizeEditWaistBack;
    private BigDecimal jacketSizeResultWaistBack;

    private BigDecimal jacketSizeRealInchShoulder;
    private BigDecimal jacketSizeRealCentiShoulder;
    private BigDecimal jacketSizeGaugeShoulder;
    private BigDecimal jacketSizeEditShoulder;
    private BigDecimal jacketSizeResultShoulder;

    private BigDecimal jacketSizeRealInchLeftSleeve;
    private BigDecimal jacketSizeRealCentiLeftSleeve;
    private BigDecimal jacketSizeGaugeLeftSleeve;
    private BigDecimal jacketSizeEditLeftSleeve;
    private BigDecimal jacketSizeResultLeftSleeve;

    private BigDecimal jacketSizeRealInchRightSleeve;
    private BigDecimal jacketSizeRealCentiRightSleeve;
    private BigDecimal jacketSizeGaugeRightSleeve;
    private BigDecimal jacketSizeEditRightSleeve;
    private BigDecimal jacketSizeResultRightSleeve;

    private BigDecimal jacketSizeRealInchBackLength;
    private BigDecimal jacketSizeRealCentiBackLength;
    private BigDecimal jacketSizeGaugeBackLength;
    private BigDecimal jacketSizeEditBackLength;
    private BigDecimal jacketSizeResultBackLength;

    private BigDecimal jacketSizeRealInchFrontLength;
    private BigDecimal jacketSizeRealCentiFrontLength;
    private BigDecimal jacketSizeGaugeFrontLength;
    private BigDecimal jacketSizeEditFrontLength;
    private BigDecimal jacketSizeResultFrontLength;
    /**
     * 상의 끝
     */

    /**
     * 하의 시작
     */
    private Long pantsGauge;

    private BigDecimal pantsFrontRound;
    private BigDecimal pantsBackRound;
    private BigDecimal pantsO;

    private BigDecimal pantsSizeRealInchWaist;
    private BigDecimal pantsSizeRealCentiWaist;
    private BigDecimal pantsSizeGaugeWaist;
    private BigDecimal pantsSizeEditWaist;
    private BigDecimal pantsSizeResultWaist;
    private BigDecimal pantsSizeEditDetailFrontWaist;
    private BigDecimal pantsSizeEditDetailBackWaist;

    private BigDecimal pantsSizeRealInchHip;
    private BigDecimal pantsSizeRealCentiHip;
    private BigDecimal pantsSizeGaugeHip;
    private BigDecimal pantsSizeEditHip;
    private BigDecimal pantsSizeResultHip;
    private BigDecimal pantsSizeEditDetailFrontHip;
    private BigDecimal pantsSizeEditDetailBackHip;

    private BigDecimal pantsSizeRealInchThigh;
    private BigDecimal pantsSizeRealCentiThigh;
    private BigDecimal pantsSizeGaugeThigh;
    private BigDecimal pantsSizeEditThigh;
    private BigDecimal pantsSizeResultThigh;
    private BigDecimal pantsSizeEditDetailFrontThigh;
    private BigDecimal pantsSizeEditDetailBackThigh;

    private BigDecimal pantsSizeRealInchRise;
    private BigDecimal pantsSizeRealCentiRise;
    private BigDecimal pantsSizeGaugeRise;
    private BigDecimal pantsSizeEditRise;
    private BigDecimal pantsSizeResultRise;
    private BigDecimal pantsSizeDetailFrontRise;
    private BigDecimal pantsSizeDetailBackRise;

    private BigDecimal pantsSizeRealInchInseam;
    private BigDecimal pantsSizeRealCentiInseam;
    private BigDecimal pantsSizeGaugeInseam;
    private BigDecimal pantsSizeEditInseam;
    private BigDecimal pantsSizeResultInseam;
    private BigDecimal pantsSizeDetailFrontInseam;
    private BigDecimal pantsSizeDetailBackInseam;

    private BigDecimal pantsSizeRealInchNee;
    private BigDecimal pantsSizeRealCentiNee;
    private BigDecimal pantsSizeGaugeNee;
    private BigDecimal pantsSizeEditNee;
    private BigDecimal pantsSizeResultNee;
    private BigDecimal pantsSizeEditDetailFrontNee;
    private BigDecimal pantsSizeEditDetailBackNee;

    private BigDecimal pantsSizeRealInchOpening;
    private BigDecimal pantsSizeRealCentiOpening;
    private BigDecimal pantsSizeGaugeOpening;
    private BigDecimal pantsSizeEditOpening;
    private BigDecimal pantsSizeResultOpening;
    private BigDecimal pantsSizeEditDetailFrontOpening;
    private BigDecimal pantsSizeEditDetailBackOpening;

    private BigDecimal pantsSizeRealInchGarmentLength;
    private BigDecimal pantsSizeRealCentiGarmentLength;
    private BigDecimal pantsSizeGaugeGarmentLength;
    private BigDecimal pantsSizeEditGarmentLength;
    private BigDecimal pantsSizeResultGarmentLength;
    /**
     * 하의 끝
     */

    /**
     * 조끼 시작
     */
    private Long vestGauge;

    private BigDecimal vestSizeFrontRound;
    private BigDecimal vestSizeBackRound;
    private BigDecimal vestSizeShoulderSlope;
    private BigDecimal vestSizeBackNeckline;
    private BigDecimal vestSizeBackNeckPointMove;
    private BigDecimal vestSizeNeckRound;
    private BigDecimal vestSizeChestLine;
    private BigDecimal vestSizeReverseZone;
    private BigDecimal vestSizeFrontLength;
    private BigDecimal vestSizeFirstButton;
    private BigDecimal vestSizeArmHole;

    private BigDecimal vestSizeRealInchBackLength;
    private BigDecimal vestSizeRealCentiBackLength;
    private BigDecimal vestSizeGaugeBackLength;
    private BigDecimal vestSizeEditBackLength;
    private BigDecimal vestSizeResultBackLength;

    private BigDecimal vestSizeRealInchChest;
    private BigDecimal vestSizeRealCentiChest;
    private BigDecimal vestSizeGaugeChest;
    private BigDecimal vestSizeEditChest;
    private BigDecimal vestSizeResultChest;

    private BigDecimal vestSizeRealInchWaist;
    private BigDecimal vestSizeRealCentiWaist;
    private BigDecimal vestSizeGaugeWaist;
    private BigDecimal vestSizeEditWaist;
    private BigDecimal vestSizeResultWaist;
    /**
     * 조끼 끝
     */

    // 사이즈 시작
    private Long coatPattern;
    private Long coatGauge;
    private BigDecimal coatSizeFrontRound;
    private BigDecimal coatSizeBackRound;
    private BigDecimal coatSizeShoulderSlope;
    private BigDecimal coatSizeBackNeckline;
    private BigDecimal coatSizeArmHole;
    private BigDecimal coatSizeBackNeckPointMove;
    private BigDecimal coatSizeUpperArm;
    private BigDecimal coatSizeMiddleArm;
    private BigDecimal coatSizeLowerArm;
    private BigDecimal coatSizeFrontLength;
    private BigDecimal coatSizeBackCenterMove;
    private BigDecimal coatSizeBackCenterShorten;
    private BigDecimal coatSizeFirstButton;
    private BigDecimal coatSizeNeckRound;
    private BigDecimal coatSizeHemLine;
    private BigDecimal coatSizeChestLine;

    private BigDecimal coatSizeRealInchChest;
    private BigDecimal coatSizeRealCentiChest;
    private BigDecimal coatSizeGaugeChest;
    private BigDecimal coatSizeEditChest;
    private BigDecimal coatSizeResultChest;

    private BigDecimal coatSizeRealInchChestWidth;
    private BigDecimal coatSizeRealCentiChestWidth;
    private BigDecimal coatSizeGaugeChestWidth;
    private BigDecimal coatSizeEditChestWidth;
    private BigDecimal coatSizeResultChestWidth;

    private BigDecimal coatSizeRealInchUpperSideBody;
    private BigDecimal coatSizeRealCentiUpperSideBody;
    private BigDecimal coatSizeGaugeUpperSideBody;
    private BigDecimal coatSizeEditUpperSideBody;
    private BigDecimal coatSizeResultUpperSideBody;

    private BigDecimal coatSizeRealInchBackWidth;
    private BigDecimal coatSizeRealCentiBackWidth;
    private BigDecimal coatSizeGaugeBackWidth;
    private BigDecimal coatSizeEditBackWidth;
    private BigDecimal coatSizeResultBackWidth;

    private BigDecimal coatSizeRealInchWaist;
    private BigDecimal coatSizeRealCentiWaist;
    private BigDecimal coatSizeGaugeWaist;
    private BigDecimal coatSizeEditWaist;
    private BigDecimal coatSizeResultWaist;

    private BigDecimal coatSizeRealInchWaistFront;
    private BigDecimal coatSizeRealCentiWaistFront;
    private BigDecimal coatSizeGaugeWaistFront;
    private BigDecimal coatSizeEditWaistFront;
    private BigDecimal coatSizeResultWaistFront;

    private BigDecimal coatSizeRealInchMiddleSideBody;
    private BigDecimal coatSizeRealCentiMiddleSideBody;
    private BigDecimal coatSizeGaugeMiddleSideBody;
    private BigDecimal coatSizeEditMiddleSideBody;
    private BigDecimal coatSizeResultMiddleSideBody;

    private BigDecimal coatSizeRealInchWaistBack;
    private BigDecimal coatSizeRealCentiWaistBack;
    private BigDecimal coatSizeGaugeWaistBack;
    private BigDecimal coatSizeEditWaistBack;
    private BigDecimal coatSizeResultWaistBack;

    private BigDecimal coatSizeRealInchShoulder;
    private BigDecimal coatSizeRealCentiShoulder;
    private BigDecimal coatSizeGaugeShoulder;
    private BigDecimal coatSizeEditShoulder;
    private BigDecimal coatSizeResultShoulder;

    private BigDecimal coatSizeRealInchLeftSleeve;
    private BigDecimal coatSizeRealCentiLeftSleeve;
    private BigDecimal coatSizeGaugeLeftSleeve;
    private BigDecimal coatSizeEditLeftSleeve;
    private BigDecimal coatSizeResultLeftSleeve;

    private BigDecimal coatSizeRealInchRightSleeve;
    private BigDecimal coatSizeRealCentiRightSleeve;
    private BigDecimal coatSizeGaugeRightSleeve;
    private BigDecimal coatSizeEditRightSleeve;
    private BigDecimal coatSizeResultRightSleeve;

    private BigDecimal coatSizeRealInchBackLength;
    private BigDecimal coatSizeRealCentiBackLength;
    private BigDecimal coatSizeGaugeBackLength;
    private BigDecimal coatSizeEditBackLength;
    private BigDecimal coatSizeResultBackLength;

    private BigDecimal coatSizeRealInchFrontLength;
    private BigDecimal coatSizeRealCentiFrontLength;
    private BigDecimal coatSizeGaugeFrontLength;
    private BigDecimal coatSizeEditFrontLength;
    private BigDecimal coatSizeResultFrontLength;
    /**
     * 코트 끝
     */
}
