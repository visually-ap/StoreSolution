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
public class ResSizeVestDTO {
    private Long id;
    private Long counselingCommon;

    // 사이즈
    private Long vestPattern;
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
    private BigDecimal vestSizeEditBackLength;

    private BigDecimal vestSizeRealInchChest;
    private BigDecimal vestSizeRealCentiChest;
    private BigDecimal vestSizeEditChest;

    private BigDecimal vestSizeRealInchWaist;
    private BigDecimal vestSizeRealCentiWaist;
    private BigDecimal vestSizeEditWaist;
}
