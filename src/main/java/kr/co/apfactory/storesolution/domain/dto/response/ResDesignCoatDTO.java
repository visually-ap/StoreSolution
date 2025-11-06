package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResDesignCoatDTO {
    private Long id;
    private Long counselingCommon;

    // 디자인
    private Long coatStyle;
    private Long coatButtonCount;
    private Long coatChestPocket;
    private Long coatShoulder;
    private Long coatFrontPocket;
    private Long coatLeftPad;
    private Long coatBackSlit;
    private Long coatRightPad;
    private Long coatSleeveButton;
    private Long coatLapel;
    private Long coatSleeve;
    private Long coatAmf;
    private Long coatLapelQ;
    private Long coatLining;
    private Long coatCheckFabric;
    private Long coatAddOption1;
    private Long coatAddOption2;

    private Long coatPattern;
}
