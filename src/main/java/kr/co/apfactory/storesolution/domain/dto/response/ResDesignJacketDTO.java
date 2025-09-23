package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResDesignJacketDTO {
    private Long id;
    private Long counselingCommon;

    // 디자인
    private Long jacketStyle;
    private Long jacketButtonCount;
    private Long jacketChestPocket;
    private Long jacketShoulder;
    private Long jacketFrontPocket;
    private Long jacketLeftPad;
    private Long jacketBackSlit;
    private Long jacketRightPad;
    private Long jacketSleeveButton;
    private Long jacketLapel;
    private Long jacketSleeve;
    private Long jacketTuxedo;
    private Long jacketAmf;
    private Long jacketLapelQ;
    private Long jacketLining;
    private Long jacketCheckFabric;
    private Long jacketAddOption;
}
