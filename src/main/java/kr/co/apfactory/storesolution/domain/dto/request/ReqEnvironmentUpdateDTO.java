package kr.co.apfactory.storesolution.domain.dto.request;

import kr.co.apfactory.storesolution.domain.entity.SiteEnvSetting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqEnvironmentUpdateDTO {

    // 상의 디자인
    private Boolean jacketStyle = false;
    private Boolean jacketButtonCount = false;
    private Boolean jacketChestPocket = false;
    private Boolean jacketShoulder = false;
    private Boolean jacketFrontPocket = false;
    private Boolean jacketLeftPad = false;
    private Boolean jacketRightPad = false;
    private Boolean jacketBackSlit = false;
    private Boolean jacketSleeveButton = false;
    private Boolean jacketLapel = false;
    private Boolean jacketAmf = false;
    private Boolean jacketSleeve = false;
    private Boolean jacketTuxedo = false;
    private Boolean jacketLapelQ = false;
    private Boolean jacketLining = false;
    private Boolean jacketCheckFabric = false;
    private Boolean jacketAddOption = false;

    // 하의 디자인
    private Boolean pantsPocket = false;
    private Boolean pantsCheckFabric = false;
    private Boolean pantsBeltLoop = false;
    private Boolean pantsFlap = false;
    private Boolean pantsFlapLength = false;
    private Boolean pantsHemShape = false;
    private Boolean pantsHemThickness = false;
    private Boolean pantsAddOption1 = false;
    private Boolean pantsAddOption2 = false;
    private Boolean pantsAddOption3 = false;

    // 조끼 디자인
    private Boolean vestFrontPocket = false;
    private Boolean vestChestPocket = false;
    private Boolean vestBack = false;
    private Boolean vestAmf = false;
    private Boolean vestLapel = false;

    // 코트 디자인
    private Boolean coatStyle = false;
    private Boolean coatButtonCount = false;
    private Boolean coatChestPocket = false;
    private Boolean coatShoulder = false;
    private Boolean coatFrontPocket = false;
    private Boolean coatLeftPad = false;
    private Boolean coatRightPad = false;
    private Boolean coatBackSlit = false;
    private Boolean coatSleeveButton = false;
    private Boolean coatLapel = false;
    private Boolean coatSleeve = false;
    private Boolean coatAmf = false;
    private Boolean coatLapelQ = false;
    private Boolean coatLining = false;
    private Boolean coatCheckFabric = false;
    private Boolean coatAddOption1 = false;
    private Boolean coatAddOption2 = false;

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
