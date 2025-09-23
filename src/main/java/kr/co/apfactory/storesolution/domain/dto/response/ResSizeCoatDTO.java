package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResSizeCoatDTO {
    private Long id;
    private Long counselingCommon;

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

    // 상동
    private BigDecimal coatSizeRealInchChest;
    private BigDecimal coatSizeRealCentiChest;
    private BigDecimal coatSizeEditChest;
    // 상앞품
    private BigDecimal coatSizeRealInchChestWidth;
    private BigDecimal coatSizeRealCentiChestWidth;
    private BigDecimal coatSizeEditChestWidth;
    // 상사이바
    private BigDecimal coatSizeRealInchUpperSideBody;
    private BigDecimal coatSizeRealCentiUpperSideBody;
    private BigDecimal coatSizeEditUpperSideBody;
    // 상뒷품
    private BigDecimal coatSizeRealInchBackWidth;
    private BigDecimal coatSizeRealCentiBackWidth;
    private BigDecimal coatSizeEditBackWidth;
    // 중동
    private BigDecimal coatSizeRealInchWaist;
    private BigDecimal coatSizeRealCentiWaist;
    private BigDecimal coatSizeEditWaist;
    // 중앞품
    private BigDecimal coatSizeRealInchWaistFront;
    private BigDecimal coatSizeRealCentiWaistFront;
    private BigDecimal coatSizeEditWaistFront;
    // 중사이바
    private BigDecimal coatSizeRealInchMiddleSideBody;
    private BigDecimal coatSizeRealCentiMiddleSideBody;
    private BigDecimal coatSizeEditMiddleSideBody;
    // 중뒷품
    private BigDecimal coatSizeRealInchWaistBack;
    private BigDecimal coatSizeRealCentiWaistBack;
    private BigDecimal coatSizeEditWaistBack;
    // 어깨
    private BigDecimal coatSizeRealInchShoulder;
    private BigDecimal coatSizeRealCentiShoulder;
    private BigDecimal coatSizeEditShoulder;
    // 소매(좌)
    private BigDecimal coatSizeRealInchLeftSleeve;
    private BigDecimal coatSizeRealCentiLeftSleeve;
    private BigDecimal coatSizeEditLeftSleeve;
    // 소매(우)
    private BigDecimal coatSizeRealInchRightSleeve;
    private BigDecimal coatSizeRealCentiRightSleeve;
    private BigDecimal coatSizeEditRightSleeve;
    // 뒷기장
    private BigDecimal coatSizeRealInchBackLength;
    private BigDecimal coatSizeRealCentiBackLength;
    private BigDecimal coatSizeEditBackLength;
    // 앞기장
    private BigDecimal coatSizeRealInchFrontLength;
    private BigDecimal coatSizeRealCentiFrontLength;
    private BigDecimal coatSizeEditFrontLength;
}
