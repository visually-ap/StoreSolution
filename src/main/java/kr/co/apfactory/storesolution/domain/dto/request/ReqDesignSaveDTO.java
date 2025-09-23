package kr.co.apfactory.storesolution.domain.dto.request;

import lombok.Data;

@Data
public class ReqDesignSaveDTO {
    private Long reservationId;

    // 상의
    private Long jacketStyle;
    private Long jacketButtonCount;
    private Long jacketChestPocket;
    private Long jacketShoulder;
    private Long jacketFrontPocket;
    private Long jacketRightPad;
    private Long jacketLeftPad;
    private Long jacketBackSlit;
    private Long jacketSleeveButton;
    private Long jacketLapel;
    private Long jacketSleeve;
    private Long jacketTuxedo;
    private Long jacketAmf;
    private Long jacketLapelQ;
    private Long jacketLining;
    private Long jacketCheckFabric;
    private Long jacketAddOption;

    // 하의
    private Long pantsPattern;
    private Long pantsBeltLoop;
    private Long pantsPocket;
    private Long pantsHemShape;
    private Long pantsHemThickness;
    private Long pantsFlap;
    private Long pantsFlapLength;
    private Long pantsCheckFabric;
    private Long pantsAddOption1;
    private Long pantsAddOption2;
    private Long pantsAddOption3;

    // 조끼
    private Long vestPattern;
    private Long vestBack;
    private Long vestFrontPocket;
    private Long vestChestPocket;
    private Long vestLapel;
    private Long vestAmf;

    // 코트
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
}
