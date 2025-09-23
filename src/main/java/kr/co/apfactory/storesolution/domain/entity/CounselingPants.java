package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqDesignSaveDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqSizeSaveDTO;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_counseling_pants")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CounselingPants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("카운셀링 하의 디자인 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("카운셀링 공통 아이디")
    private CounselingCommon counselingCommon;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("하의 패턴 시퀀스")
    private Pattern pantsPattern;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("게이지복")
    private PatternSize pantsGauge;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("벨트고리")
    private CommonCodeChild pantsBeltLoop;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("주머니")
    private CommonCodeChild pantsPocket;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("밑단모양")
    private CommonCodeChild pantsHemShape;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("두께")
    private CommonCodeChild pantsHemThickness;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("비죠")
    private CommonCodeChild pantsFlap;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("(비죠) 길이")
    private CommonCodeChild pantsFlapLength;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("체크원단")
    private CommonCodeChild pantsCheckFabric;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("추가옵션 1")
    private CommonCodeChild pantsAddOption1;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("추가옵션 2")
    private CommonCodeChild pantsAddOption2;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("추가옵션 3")
    private CommonCodeChild pantsAddOption3;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("굴신")
    private BigDecimal pantsFrontRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("반신")
    private BigDecimal pantsBackRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("O자")
    private BigDecimal pantsO;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 허리")
    private BigDecimal pantsSizeRealInchWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 허리")
    private BigDecimal pantsSizeRealCentiWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 허리")
    private BigDecimal pantsSizeEditWaist;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 앞판 수정값 허리")
    private BigDecimal pantsSizeEditDetailFrontWaist;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 뒷판 수정값 허리")
    private BigDecimal pantsSizeEditDetailBackWaist;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 힙")
    private BigDecimal pantsSizeRealInchHip;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 힙")
    private BigDecimal pantsSizeRealCentiHip;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 힙")
    private BigDecimal pantsSizeEditHip;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 앞판 수정값 힙")
    private BigDecimal pantsSizeEditDetailFrontHip;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 뒷판 수정값 힙")
    private BigDecimal pantsSizeEditDetailBackHip;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 허벅")
    private BigDecimal pantsSizeRealInchThigh;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 허벅")
    private BigDecimal pantsSizeRealCentiThigh;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 허벅")
    private BigDecimal pantsSizeEditThigh;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 앞판 수정값 허벅")
    private BigDecimal pantsSizeEditDetailFrontThigh;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 뒷판 수정값 허벅")
    private BigDecimal pantsSizeEditDetailBackThigh;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 앞상")
    private BigDecimal pantsSizeRealInchRise;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 앞상")
    private BigDecimal pantsSizeRealCentiRise;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 앞상")
    private BigDecimal pantsSizeEditRise;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("세부 수정 앞판 수정값 앞상(앞판꼬리)")
    private BigDecimal pantsSizeDetailFrontRise;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("세부 수정 뒷판 수정값 앞상(사용안함 : 예비)")
    private BigDecimal pantsSizeDetailBackRise;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 뒷상")
    private BigDecimal pantsSizeRealInchInseam;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 뒷상")
    private BigDecimal pantsSizeRealCentiInseam;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 뒷상")
    private BigDecimal pantsSizeEditInseam;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("세부 수정 앞판 수정값 뒷상(뒷판꼬리)")
    private BigDecimal pantsSizeDetailFrontInseam;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("세부 수정 뒷판 수정값 뒷상(똥꼬파기)")
    private BigDecimal pantsSizeDetailBackInseam;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 무릎")
    private BigDecimal pantsSizeRealInchNee;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 무릎")
    private BigDecimal pantsSizeRealCentiNee;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 무릎")
    private BigDecimal pantsSizeEditNee;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 앞판 수정값 무릎")
    private BigDecimal pantsSizeEditDetailFrontNee;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 뒷판 수정값 무릎")
    private BigDecimal pantsSizeEditDetailBackNee;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 부리")
    private BigDecimal pantsSizeRealInchOpening;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 부리")
    private BigDecimal pantsSizeRealCentiOpening;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 부리")
    private BigDecimal pantsSizeEditOpening;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 앞판 수정값 부리")
    private BigDecimal pantsSizeEditDetailFrontOpening;

    @Column(columnDefinition = "decimal(5,2) default 0")
    @Comment("세부 수정 뒷판 수정값 부리")
    private BigDecimal pantsSizeEditDetailBackOpening;


    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 총기장")
    private BigDecimal pantsSizeRealInchGarmentLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 총기장")
    private BigDecimal pantsSizeRealCentiGarmentLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정값 총기장")
    private BigDecimal pantsSizeEditGarmentLength;

    public void updateDesignData(ReqDesignSaveDTO dto) {
        this.pantsPattern = Pattern.builder().id(dto.getPantsPattern()).build();
        this.pantsPocket = CommonCodeChild.builder().id(dto.getPantsPocket()).build();
        this.pantsCheckFabric = CommonCodeChild.builder().id(dto.getPantsCheckFabric()).build();
        this.pantsBeltLoop = CommonCodeChild.builder().id(dto.getPantsBeltLoop()).build();
        this.pantsFlap = CommonCodeChild.builder().id(dto.getPantsFlap()).build();
        this.pantsFlapLength = CommonCodeChild.builder().id(dto.getPantsFlapLength()).build();
        this.pantsHemShape = CommonCodeChild.builder().id(dto.getPantsHemShape()).build();
        this.pantsHemThickness = CommonCodeChild.builder().id(dto.getPantsHemThickness()).build();
        this.pantsAddOption1 = CommonCodeChild.builder().id(dto.getPantsAddOption1()).build();
        this.pantsAddOption2 = CommonCodeChild.builder().id(dto.getPantsAddOption2()).build();
        this.pantsAddOption3 = CommonCodeChild.builder().id(dto.getPantsAddOption3()).build();
    }

    public void initDesignData() {
        this.pantsPattern = null;
        this.pantsPocket = null;
        this.pantsCheckFabric = null;
        this.pantsBeltLoop = null;
        this.pantsFlap = null;
        this.pantsFlapLength = null;
        this.pantsHemShape = null;
        this.pantsHemThickness = null;
        this.pantsAddOption1 = null;
        this.pantsAddOption2 = null;
        this.pantsAddOption3 = null;
    }

    public void updateSizeData(ReqSizeSaveDTO dto) {
        this.pantsGauge = dto.getPantsGauge() == null ? null : PatternSize.builder().id(dto.getPantsGauge()).build();

        this.pantsSizeRealInchWaist = dto.getPantsSizeRealInchWaist() == null ? BigDecimal.ZERO : dto.getPantsSizeRealInchWaist();
        this.pantsSizeRealCentiWaist = dto.getPantsSizeRealCentiWaist() == null ? BigDecimal.ZERO : dto.getPantsSizeRealCentiWaist();
        this.pantsSizeEditWaist = dto.getPantsSizeEditWaist() == null ? BigDecimal.ZERO : dto.getPantsSizeEditWaist();
        this.pantsSizeEditDetailFrontWaist = dto.getPantsSizeEditDetailFrontWaist() == null ? BigDecimal.ZERO : dto.getPantsSizeEditDetailFrontWaist();
        this.pantsSizeEditDetailBackWaist = dto.getPantsSizeEditDetailBackWaist() == null ? BigDecimal.ZERO : dto.getPantsSizeEditDetailBackWaist();

        this.pantsSizeRealInchHip = dto.getPantsSizeRealInchHip() == null ? BigDecimal.ZERO : dto.getPantsSizeRealInchHip();
        this.pantsSizeRealCentiHip = dto.getPantsSizeRealCentiHip() == null ? BigDecimal.ZERO : dto.getPantsSizeRealCentiHip();
        this.pantsSizeEditHip = dto.getPantsSizeEditHip() == null ? BigDecimal.ZERO : dto.getPantsSizeEditHip();
        this.pantsSizeEditDetailFrontHip = dto.getPantsSizeEditDetailFrontHip() == null ? BigDecimal.ZERO : dto.getPantsSizeEditDetailFrontHip();
        this.pantsSizeEditDetailBackHip = dto.getPantsSizeEditDetailBackHip() == null ? BigDecimal.ZERO : dto.getPantsSizeEditDetailBackHip();

        this.pantsSizeRealInchThigh = dto.getPantsSizeRealInchThigh() == null ? BigDecimal.ZERO : dto.getPantsSizeRealInchThigh();
        this.pantsSizeRealCentiThigh = dto.getPantsSizeRealCentiThigh() == null ? BigDecimal.ZERO : dto.getPantsSizeRealCentiThigh();
        this.pantsSizeEditThigh = dto.getPantsSizeEditThigh() == null ? BigDecimal.ZERO : dto.getPantsSizeEditThigh();
        this.pantsSizeEditDetailFrontThigh = dto.getPantsSizeEditDetailFrontThigh() == null ? BigDecimal.ZERO : dto.getPantsSizeEditDetailFrontThigh();
        this.pantsSizeEditDetailBackThigh = dto.getPantsSizeEditDetailBackThigh() == null ? BigDecimal.ZERO : dto.getPantsSizeEditDetailBackThigh();

        this.pantsSizeRealInchRise = dto.getPantsSizeRealInchRise() == null ? BigDecimal.ZERO : dto.getPantsSizeRealInchRise();
        this.pantsSizeRealCentiRise = dto.getPantsSizeRealCentiRise() == null ? BigDecimal.ZERO : dto.getPantsSizeRealCentiRise();
        this.pantsSizeEditRise = dto.getPantsSizeEditRise() == null ? BigDecimal.ZERO : dto.getPantsSizeEditRise();
        this.pantsSizeDetailFrontRise = dto.getPantsSizeDetailFrontRise() == null ? BigDecimal.ZERO : dto.getPantsSizeDetailFrontRise();
        this.pantsSizeDetailBackRise = dto.getPantsSizeDetailBackRise() == null ? BigDecimal.ZERO : dto.getPantsSizeDetailBackRise();

        this.pantsSizeRealInchInseam = dto.getPantsSizeRealInchInseam() == null ? BigDecimal.ZERO : dto.getPantsSizeRealInchInseam();
        this.pantsSizeRealCentiInseam = dto.getPantsSizeRealCentiInseam() == null ? BigDecimal.ZERO : dto.getPantsSizeRealCentiInseam();
        this.pantsSizeEditInseam = dto.getPantsSizeEditInseam() == null ? BigDecimal.ZERO : dto.getPantsSizeEditInseam();
        this.pantsSizeDetailFrontInseam = dto.getPantsSizeDetailFrontInseam() == null ? BigDecimal.ZERO : dto.getPantsSizeDetailFrontInseam();
        this.pantsSizeDetailBackInseam = dto.getPantsSizeDetailBackInseam() == null ? BigDecimal.ZERO : dto.getPantsSizeDetailBackInseam();

        this.pantsSizeRealInchNee = dto.getPantsSizeRealInchNee() == null ? BigDecimal.ZERO : dto.getPantsSizeRealInchNee();
        this.pantsSizeRealCentiNee = dto.getPantsSizeRealCentiNee() == null ? BigDecimal.ZERO : dto.getPantsSizeRealCentiNee();
        this.pantsSizeEditNee = dto.getPantsSizeEditNee() == null ? BigDecimal.ZERO : dto.getPantsSizeEditNee();
        this.pantsSizeEditDetailFrontNee = dto.getPantsSizeEditDetailFrontNee() == null ? BigDecimal.ZERO : dto.getPantsSizeEditDetailFrontNee();
        this.pantsSizeEditDetailBackNee = dto.getPantsSizeEditDetailBackNee() == null ? BigDecimal.ZERO : dto.getPantsSizeEditDetailBackNee();

        this.pantsSizeRealInchOpening = dto.getPantsSizeRealInchOpening() == null ? BigDecimal.ZERO : dto.getPantsSizeRealInchOpening();
        this.pantsSizeRealCentiOpening = dto.getPantsSizeRealCentiOpening() == null ? BigDecimal.ZERO : dto.getPantsSizeRealCentiOpening();
        this.pantsSizeEditOpening = dto.getPantsSizeEditOpening() == null ? BigDecimal.ZERO : dto.getPantsSizeEditOpening();
        this.pantsSizeEditDetailFrontOpening = dto.getPantsSizeEditDetailFrontOpening() == null ? BigDecimal.ZERO : dto.getPantsSizeEditDetailFrontOpening();
        this.pantsSizeEditDetailBackOpening = dto.getPantsSizeEditDetailBackOpening() == null ? BigDecimal.ZERO : dto.getPantsSizeEditDetailBackOpening();

        this.pantsSizeRealInchGarmentLength = dto.getPantsSizeRealInchGarmentLength() == null ? BigDecimal.ZERO : dto.getPantsSizeRealInchGarmentLength();
        this.pantsSizeRealCentiGarmentLength = dto.getPantsSizeRealCentiGarmentLength() == null ? BigDecimal.ZERO : dto.getPantsSizeRealCentiGarmentLength();
        this.pantsSizeEditGarmentLength = dto.getPantsSizeEditGarmentLength() == null ? BigDecimal.ZERO : dto.getPantsSizeEditGarmentLength();

        this.pantsFrontRound = dto.getPantsFrontRound() == null ? BigDecimal.ZERO : dto.getPantsFrontRound();
        this.pantsBackRound = dto.getPantsBackRound() == null ? BigDecimal.ZERO : dto.getPantsBackRound();;
        this.pantsO = dto.getPantsO() == null ? BigDecimal.ZERO : dto.getPantsO();;
    }
}
