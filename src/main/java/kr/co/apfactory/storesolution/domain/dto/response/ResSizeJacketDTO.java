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
public class ResSizeJacketDTO {
    private Long id;
    private Long counselingCommon;
    private Long jacketPattern;

    // 사이즈
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

    // 상동
    private BigDecimal jacketSizeRealInchChest;
    private BigDecimal jacketSizeRealCentiChest;
    private BigDecimal jacketSizeEditChest;
    // 상앞품
    private BigDecimal jacketSizeRealInchChestWidth;
    private BigDecimal jacketSizeRealCentiChestWidth;
    private BigDecimal jacketSizeEditChestWidth;
    // 상사이바
    private BigDecimal jacketSizeRealInchUpperSideBody;
    private BigDecimal jacketSizeRealCentiUpperSideBody;
    private BigDecimal jacketSizeEditUpperSideBody;
    // 상뒷품
    private BigDecimal jacketSizeRealInchBackWidth;
    private BigDecimal jacketSizeRealCentiBackWidth;
    private BigDecimal jacketSizeEditBackWidth;
    // 중동
    private BigDecimal jacketSizeRealInchWaist;
    private BigDecimal jacketSizeRealCentiWaist;
    private BigDecimal jacketSizeEditWaist;
    // 중앞품
    private BigDecimal jacketSizeRealInchWaistFront;
    private BigDecimal jacketSizeRealCentiWaistFront;
    private BigDecimal jacketSizeEditWaistFront;
    // 중사이바
    private BigDecimal jacketSizeRealInchMiddleSideBody;
    private BigDecimal jacketSizeRealCentiMiddleSideBody;
    private BigDecimal jacketSizeEditMiddleSideBody;
    // 중뒷품
    private BigDecimal jacketSizeRealInchWaistBack;
    private BigDecimal jacketSizeRealCentiWaistBack;
    private BigDecimal jacketSizeEditWaistBack;
    // 어깨
    private BigDecimal jacketSizeRealInchShoulder;
    private BigDecimal jacketSizeRealCentiShoulder;
    private BigDecimal jacketSizeEditShoulder;
    // 소매(좌)
    private BigDecimal jacketSizeRealInchLeftSleeve;
    private BigDecimal jacketSizeRealCentiLeftSleeve;
    private BigDecimal jacketSizeEditLeftSleeve;
    // 소매(우)
    private BigDecimal jacketSizeRealInchRightSleeve;
    private BigDecimal jacketSizeRealCentiRightSleeve;
    private BigDecimal jacketSizeEditRightSleeve;
    // 뒷기장
    private BigDecimal jacketSizeRealInchBackLength;
    private BigDecimal jacketSizeRealCentiBackLength;
    private BigDecimal jacketSizeEditBackLength;
    // 앞기장
    private BigDecimal jacketSizeRealInchFrontLength;
    private BigDecimal jacketSizeRealCentiFrontLength;
    private BigDecimal jacketSizeEditFrontLength;
}
