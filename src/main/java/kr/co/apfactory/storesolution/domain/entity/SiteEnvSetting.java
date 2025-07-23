package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.response.ResEnvironmentUpdateDTO;
import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttach;
import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttachMaster;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_site_env_setting")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class SiteEnvSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("매장 시퀀스")
    private Store store;

    // 상의
    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 스타일")
    private Boolean jacketStyle;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 단추갯수")
    private Boolean jacketButtonCount;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 가슴포켓")
    private Boolean jacketChestPocket;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 어깨")
    private Boolean jacketShoulder;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 앞주머니")
    private Boolean jacketFrontPocket;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 패드(좌)")
    private Boolean jacketLeftPad;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 뒷트임")
    private Boolean jacketBackSlit;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 패드(우)")
    private Boolean jacketRightPad;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 소매단추")
    private Boolean jacketSleeveButton;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 라펠너비")
    private Boolean jacketLapel;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 소매디자인")
    private Boolean jacketSleeve;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 턱시도")
    private Boolean jacketTuxedo;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 amf")
    private Boolean jacketAmf;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 라펠디자인")
    private Boolean jacketLapelQ;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 안감")
    private Boolean jacketLining;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 원단무늬")
    private Boolean jacketCheckFabric;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("상의 추가옵션")
    private Boolean jacketAddOption;

    // 하의
    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("하의 주머니")
    private Boolean pantsPocket;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("하의 원단무늬")
    private Boolean pantsCheckFabric;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("하의 벨트고리")
    private Boolean pantsBeltLoop;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("하의 비죠")
    private Boolean pantsFlap;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("하의 비죠길이")
    private Boolean pantsFlapLength;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("하의 밑단모양")
    private Boolean pantsHemShape;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("하의 밑단두께")
    private Boolean pantsHemThickness;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("하의 추가옵션1")
    private Boolean pantsAddOption1;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("하의 추가옵션2")
    private Boolean pantsAddOption2;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("하의 추가옵션3")
    private Boolean pantsAddOption3;

    // 조끼
    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("조끼 앞주머니")
    private Boolean vestFrontPocket;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("조끼 가슴포켓")
    private Boolean vestChestPocket;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("조끼 뒷판")
    private Boolean vestBack;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("조끼 호시")
    private Boolean vestAmf;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("조끼 라펠")
    private Boolean vestLapel;

    // 코트
    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 스타일")
    private Boolean coatStyle;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 단추갯수")
    private Boolean coatButtonCount;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 가슴포켓")
    private Boolean coatChestPocket;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 어깨")
    private Boolean coatShoulder;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 앞주머니")
    private Boolean coatFrontPocket;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 패드(좌)")
    private Boolean coatLeftPad;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 뒷트임")
    private Boolean coatBackSlit;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 패드(우)")
    private Boolean coatRightPad;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 소매단추")
    private Boolean coatSleeveButton;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 라펠너비")
    private Boolean coatLapel;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 소매디자인")
    private Boolean coatSleeve;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 라펠디자인")
    private Boolean coatLapelQ;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 (AMF)호시")
    private Boolean coatAmf;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 원단무늬")
    private Boolean coatCheckFabric;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 안감")
    private Boolean coatLining;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 추가옵션1")
    private Boolean coatAddOption1;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("코트 추가옵션2")
    private Boolean coatAddOption2;

    @Column(length = 10, nullable = false)
    @Comment("항목1 색상")
    private String typeColor1;

    @Column(columnDefinition = "boolean default 10", nullable = false)
    @Comment("항목1 시간")
    private Integer typeTime1;

    @Column(length = 10, nullable = false)
    @Comment("항목2 색상")
    private String typeColor2;

    @Column(columnDefinition = "boolean default 10", nullable = false)
    @Comment("항목2 시간")
    private Integer typeTime2;

    @Column(length = 10, nullable = false)
    @Comment("항목3 색상")
    private String typeColor3;

    @Column(columnDefinition = "boolean default 10", nullable = false)
    @Comment("항목3 시간")
    private Integer typeTime3;

    @Column(length = 10, nullable = false)
    @Comment("항목4 색상")
    private String typeColor4;

    @Column(columnDefinition = "boolean default 10", nullable = false)
    @Comment("항목4 시간")
    private Integer typeTime4;

    @Column(length = 10, nullable = false)
    @Comment("항목5 색상")
    private String typeColor5;

    @Column(columnDefinition = "boolean default 10", nullable = false)
    @Comment("항목5 시간")
    private Integer typeTime5;

    @Column(length = 10, nullable = false)
    @Comment("항목6 색상")
    private String typeColor6;

    @Column(columnDefinition = "boolean default 10", nullable = false)
    @Comment("항목6 시간")
    private Integer typeTime6;

    public ResEnvironmentUpdateDTO toResEnvironmentUpdateDTO() {
        return ResEnvironmentUpdateDTO.builder()
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

    public void updateStore(Store store) {
        this.store = store;
    }
}
