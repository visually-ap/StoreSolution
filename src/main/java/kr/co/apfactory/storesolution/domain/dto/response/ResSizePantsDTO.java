package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResSizePantsDTO {
    private Long id;
    private Long counselingCommon;
    private Long pantsPattern;

    // 사이즈
    private Long pantsGauge;
    private BigDecimal pantsFrontRound;
    private BigDecimal pantsBackRound;
    private BigDecimal pantsO;

    private BigDecimal pantsSizeRealInchWaist;
    private BigDecimal pantsSizeRealCentiWaist;
    private BigDecimal pantsSizeEditWaist;
    private BigDecimal pantsSizeEditDetailFrontWaist;
    private BigDecimal pantsSizeEditDetailBackWaist;

    private BigDecimal pantsSizeRealInchHip;
    private BigDecimal pantsSizeRealCentiHip;
    private BigDecimal pantsSizeEditHip;
    private BigDecimal pantsSizeEditDetailFrontHip;
    private BigDecimal pantsSizeEditDetailBackHip;

    private BigDecimal pantsSizeRealInchThigh;
    private BigDecimal pantsSizeRealCentiThigh;
    private BigDecimal pantsSizeEditThigh;
    private BigDecimal pantsSizeEditDetailFrontThigh;
    private BigDecimal pantsSizeEditDetailBackThigh;

    private BigDecimal pantsSizeRealInchRise;
    private BigDecimal pantsSizeRealCentiRise;
    private BigDecimal pantsSizeEditRise;
    private BigDecimal pantsSizeDetailFrontRise;
    private BigDecimal pantsSizeDetailBackRise;

    private BigDecimal pantsSizeRealInchInseam;
    private BigDecimal pantsSizeRealCentiInseam;
    private BigDecimal pantsSizeEditInseam;
    private BigDecimal pantsSizeDetailFrontInseam;
    private BigDecimal pantsSizeDetailBackInseam;

    private BigDecimal pantsSizeRealInchNee;
    private BigDecimal pantsSizeRealCentiNee;
    private BigDecimal pantsSizeEditNee;
    private BigDecimal pantsSizeEditDetailFrontNee;
    private BigDecimal pantsSizeEditDetailBackNee;

    private BigDecimal pantsSizeRealInchOpening;
    private BigDecimal pantsSizeRealCentiOpening;
    private BigDecimal pantsSizeEditOpening;
    private BigDecimal pantsSizeEditDetailFrontOpening;
    private BigDecimal pantsSizeEditDetailBackOpening;

    private BigDecimal pantsSizeRealInchGarmentLength;
    private BigDecimal pantsSizeRealCentiGarmentLength;
    private BigDecimal pantsSizeEditGarmentLength;
}
