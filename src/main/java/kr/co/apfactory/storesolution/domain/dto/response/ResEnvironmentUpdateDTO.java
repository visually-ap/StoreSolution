package kr.co.apfactory.storesolution.domain.dto.response;

import kr.co.apfactory.storesolution.domain.entity.SiteEnvSetting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResEnvironmentUpdateDTO {

    // 상의 디자인
    private Boolean jacketStyle;
    private Boolean jacketButtonCount;
    private Boolean jacketChestPocket;
    private Boolean jacketShoulder;
    private Boolean jacketFrontPocket;
    private Boolean jacketLeftPad;
    private Boolean jacketRightPad;
    private Boolean jacketBackSlit;
    private Boolean jacketSleeveButton;
    private Boolean jacketLapel;
    private Boolean jacketAmf;
    private Boolean jacketSleeve;
    private Boolean jacketTuxedo;
    private Boolean jacketLapelQ;
    private Boolean jacketLining;
    private Boolean jacketCheckFabric;
    private Boolean jacketAddOption;

    // 하의 디자인
    private Boolean pantsPocket;
    private Boolean pantsCheckFabric;
    private Boolean pantsBeltLoop;
    private Boolean pantsFlap;
    private Boolean pantsFlapLength;
    private Boolean pantsHemShape;
    private Boolean pantsHemThickness;
    private Boolean pantsAddOption1;
    private Boolean pantsAddOption2;
    private Boolean pantsAddOption3;

    // 조끼 디자인
    private Boolean vestFrontPocket;
    private Boolean vestChestPocket;
    private Boolean vestBack;
    private Boolean vestAmf;
    private Boolean vestLapel;

    // 코트 디자인
    private Boolean coatStyle;
    private Boolean coatButtonCount;
    private Boolean coatChestPocket;
    private Boolean coatShoulder;
    private Boolean coatFrontPocket;
    private Boolean coatLeftPad;
    private Boolean coatRightPad;
    private Boolean coatBackSlit;
    private Boolean coatSleeveButton;
    private Boolean coatLapel;
    private Boolean coatSleeve;
    private Boolean coatAmf;
    private Boolean coatLapelQ;
    private Boolean coatLining;
    private Boolean coatCheckFabric;
    private Boolean coatAddOption1;
    private Boolean coatAddOption2;

    // 항목별 색상 및 시간 설정
    private String typeColor1;
    private Integer typeTime1;
    private String typeColor2;
    private Integer typeTime2;
    private String typeColor3;
    private Integer typeTime3;
    private String typeColor4;
    private Integer typeTime4;
    private String typeColor5;
    private Integer typeTime5;
    private String typeColor6;
    private Integer typeTime6;

    public SiteEnvSetting toSiteEnvSettingEntity() {
        return SiteEnvSetting.builder()
                .jacketStyle(this.jacketStyle)
                .jacketButtonCount(this.jacketButtonCount)
                .jacketChestPocket(this.jacketChestPocket)
                .jacketShoulder(this.jacketShoulder)
                .jacketFrontPocket(this.jacketFrontPocket)
                .jacketLeftPad(this.jacketLeftPad)
                .jacketRightPad(this.jacketRightPad)
                .jacketBackSlit(this.jacketBackSlit)
                .jacketSleeveButton(this.jacketSleeveButton)
                .jacketLapel(this.jacketLapel)
                .jacketSleeve(this.jacketSleeve)
                .jacketTuxedo(this.jacketTuxedo)
                .jacketAmf(this.jacketAmf)
                .jacketLapelQ(this.jacketLapelQ)
                .jacketLining(this.jacketLining)
                .jacketCheckFabric(this.jacketCheckFabric)
                .jacketAddOption(this.jacketAddOption)
                .pantsPocket(this.pantsPocket)
                .pantsCheckFabric(this.pantsCheckFabric)
                .pantsBeltLoop(this.pantsBeltLoop)
                .pantsFlap(this.pantsFlap)
                .pantsFlapLength(this.pantsFlapLength)
                .pantsHemShape(this.pantsHemShape)
                .pantsHemThickness(this.pantsHemThickness)
                .pantsAddOption1(this.pantsAddOption1)
                .pantsAddOption2(this.pantsAddOption2)
                .pantsAddOption3(this.pantsAddOption3)
                .vestFrontPocket(this.vestFrontPocket)
                .vestChestPocket(this.vestChestPocket)
                .vestBack(this.vestBack)
                .vestAmf(this.vestAmf)
                .vestLapel(this.vestLapel)
                .coatStyle(this.coatStyle)
                .coatButtonCount(this.coatButtonCount)
                .coatChestPocket(this.coatChestPocket)
                .coatShoulder(this.coatShoulder)
                .coatFrontPocket(this.coatFrontPocket)
                .coatLeftPad(this.coatLeftPad)
                .coatRightPad(this.coatRightPad)
                .coatBackSlit(this.coatBackSlit)
                .coatSleeveButton(this.coatSleeveButton)
                .coatLapel(this.coatLapel)
                .coatSleeve(this.coatSleeve)
                .coatAmf(this.coatAmf)
                .coatLapelQ(this.coatLapelQ)
                .coatLining(this.coatLining)
                .coatCheckFabric(this.coatCheckFabric)
                .coatAddOption1(this.coatAddOption1)
                .coatAddOption2(this.coatAddOption2)
                .typeColor1(this.typeColor1)
                .typeTime1(this.typeTime1)
                .typeColor2(this.typeColor2)
                .typeTime2(this.typeTime2)
                .typeColor3(this.typeColor3)
                .typeTime3(this.typeTime3)
                .typeColor4(this.typeColor4)
                .typeTime4(this.typeTime4)
                .typeColor5(this.typeColor5)
                .typeTime5(this.typeTime5)
                .typeColor6(this.typeColor6)
                .typeTime6(this.typeTime6)
                .build();
    }
}
