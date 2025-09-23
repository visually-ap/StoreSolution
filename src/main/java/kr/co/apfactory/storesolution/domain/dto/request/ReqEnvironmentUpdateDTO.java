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
    private String typeName2;
    private String typeColor2;
    private Integer typeTime2;
    private String typeName3;
    private String typeColor3;
    private Integer typeTime3;
    private String typeName4;
    private String typeColor4;
    private Integer typeTime4;
    private String typeName5;
    private String typeColor5;
    private Integer typeTime5;
    private String typeName6;
    private String typeColor6;
    private Integer typeTime6;
}
