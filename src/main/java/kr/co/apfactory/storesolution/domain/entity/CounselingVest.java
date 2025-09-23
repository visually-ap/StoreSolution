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
@Table(name = "tb_counseling_vest")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CounselingVest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("카운셀링 하의 디자인 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("카운셀링 공통 아이디")
    private CounselingCommon counselingCommon;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("조끼 패턴 시퀀스")
    private Pattern vestPattern;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("조끼 게이지복 시퀀스")
    private PatternSize vestGauge;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("뒷판")
    private CommonCodeChild vestBack;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("앞주머니")
    private CommonCodeChild vestFrontPocket;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("가슴포켓")
    private CommonCodeChild vestChestPocket;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("라펠")
    private CommonCodeChild vestLapel;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("amf 호시")
    private CommonCodeChild vestAmf;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 뒷기장")
    private BigDecimal vestSizeRealInchBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 뒷기장")
    private BigDecimal vestSizeRealCentiBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 뒷기장")
    private BigDecimal vestSizeEditBackLength;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 상동")
    private BigDecimal vestSizeRealInchChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 상동")
    private BigDecimal vestSizeRealCentiChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 상동")
    private BigDecimal vestSizeEditChest;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 인치 중동")
    private BigDecimal vestSizeRealInchWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("실측 센티 중동")
    private BigDecimal vestSizeRealCentiWaist;

    @Column(columnDefinition = "decimal(4,1) default 0")
    @Comment("수정량 중동")
    private BigDecimal vestSizeEditWaist;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("굴신")
    private BigDecimal vestSizeFrontRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("반신")
    private BigDecimal vestSizeBackRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("견도")
    private BigDecimal vestSizeShoulderSlope;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷고대")
    private BigDecimal vestSizeBackNeckline;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("뒷목점이동")
    private BigDecimal vestSizeBackNeckPointMove;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("목둘레")
    private BigDecimal vestSizeNeckRound;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("우찌")
    private BigDecimal vestSizeChestLine;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("역V존")
    private BigDecimal vestSizeReverseZone;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("앞기장")
    private BigDecimal vestSizeFrontLength;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("첫단추")
    private BigDecimal vestSizeFirstButton;

    @Column(columnDefinition = "decimal(3,1) default 0")
    @Comment("암홀수정")
    private BigDecimal vestSizeArmHole;

    public void updateDesignData(ReqDesignSaveDTO dto) {
        this.vestPattern = Pattern.builder().id(dto.getVestPattern()).build();
        this.vestFrontPocket = CommonCodeChild.builder().id(dto.getVestFrontPocket()).build();
        this.vestChestPocket = CommonCodeChild.builder().id(dto.getVestChestPocket()).build();
        this.vestBack = CommonCodeChild.builder().id(dto.getVestBack()).build();
        this.vestAmf = CommonCodeChild.builder().id(dto.getVestAmf()).build();
        this.vestLapel = CommonCodeChild.builder().id(dto.getVestLapel()).build();
    }

    public void initDesignData() {
        this.vestPattern = null;
        this.vestFrontPocket = null;
        this.vestChestPocket = null;
        this.vestBack = null;
        this.vestAmf = null;
        this.vestLapel = null;
    }

    public void updateSizeData(ReqSizeSaveDTO dto) {
        this.vestGauge = dto.getVestGauge() == null ? null : PatternSize.builder().id(dto.getVestGauge()).build();
        this.vestSizeRealInchBackLength = dto.getVestSizeRealInchBackLength() == null ? BigDecimal.ZERO : dto.getVestSizeRealInchBackLength();
        this.vestSizeRealCentiBackLength = dto.getVestSizeRealCentiBackLength() == null ? BigDecimal.ZERO : dto.getVestSizeRealCentiBackLength();
        this.vestSizeEditBackLength = dto.getVestSizeEditBackLength() == null ? BigDecimal.ZERO : dto.getVestSizeEditBackLength();

        this.vestSizeRealInchChest = dto.getVestSizeRealInchChest() == null ? BigDecimal.ZERO : dto.getVestSizeRealInchChest();
        this.vestSizeRealCentiChest = dto.getVestSizeRealCentiChest() == null ? BigDecimal.ZERO : dto.getVestSizeRealCentiChest();
        this.vestSizeEditChest = dto.getVestSizeEditChest() == null ? BigDecimal.ZERO : dto.getVestSizeEditChest();

        this.vestSizeRealInchWaist = dto.getVestSizeRealInchWaist() == null ? BigDecimal.ZERO : dto.getVestSizeRealInchWaist();
        this.vestSizeRealCentiWaist = dto.getVestSizeRealCentiWaist() == null ? BigDecimal.ZERO : dto.getVestSizeRealCentiWaist();
        this.vestSizeEditWaist = dto.getVestSizeEditWaist() == null ? BigDecimal.ZERO : dto.getVestSizeEditWaist();

        this.vestSizeFrontRound = dto.getVestSizeFrontRound() == null ? BigDecimal.ZERO : dto.getVestSizeFrontRound();
        this.vestSizeBackRound = dto.getVestSizeBackRound() == null ? BigDecimal.ZERO : dto.getVestSizeBackRound();
        this.vestSizeShoulderSlope = dto.getVestSizeShoulderSlope() == null ? BigDecimal.ZERO : dto.getVestSizeShoulderSlope();
        this.vestSizeBackNeckline = dto.getVestSizeBackNeckline() == null ? BigDecimal.ZERO : dto.getVestSizeBackNeckline();
        this.vestSizeBackNeckPointMove = dto.getVestSizeBackNeckPointMove() == null ? BigDecimal.ZERO : dto.getVestSizeBackNeckPointMove();
        this.vestSizeNeckRound = dto.getVestSizeNeckRound() == null ? BigDecimal.ZERO : dto.getVestSizeNeckRound();
        this.vestSizeChestLine = dto.getVestSizeChestLine() == null ? BigDecimal.ZERO : dto.getVestSizeChestLine();
        this.vestSizeReverseZone = dto.getVestSizeReverseZone() == null ? BigDecimal.ZERO : dto.getVestSizeReverseZone();
        this.vestSizeFrontLength = dto.getVestSizeFrontLength() == null ? BigDecimal.ZERO : dto.getVestSizeFrontLength();
        this.vestSizeFirstButton = dto.getVestSizeFirstButton() == null ? BigDecimal.ZERO : dto.getVestSizeFirstButton();
        this.vestSizeArmHole = dto.getVestSizeArmHole() == null ? BigDecimal.ZERO : dto.getVestSizeArmHole();
    }
}
