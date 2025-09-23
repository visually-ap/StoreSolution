package kr.co.apfactory.storesolution.domain.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_basis")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class OrderBasis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("시퀀스")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("주문 공통 시퀀스")
    private OrderCommon orderCommon;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("제작방식")
    private CommonCodeChild makingType;

    @Column(length = 200)
    @Comment("담당자 이름")
    private String picName;

    @Column(length = 200)
    @Comment("체촌자 이름")
    private String measurePic;

    @Column(columnDefinition = "decimal(3) default 0", nullable = false)
    @Comment("키")
    private BigDecimal height;

    @Column(columnDefinition = "decimal(3) default 0", nullable = false)
    @Comment("몸무게")
    private BigDecimal weight;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("나이대")
    private CommonCodeChild ageGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("일정필수")
    private CommonCodeChild schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("선택단추")
    private CommonCodeChild button;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("안감")
    private CommonCodeChild lining;

    @Column(length = 100)
    @Comment("원단 회사")
    private String fabricCompany;

    @Column(length = 100)
    @Comment("원단 품번")
    private String fabricPattern;

    @Column(length = 100)
    @Comment("원단 색상")
    private String fabricColor;

    @Column(columnDefinition = "decimal(3,1) default 0", nullable = false)
    @Comment("요척")
    private BigDecimal yard;

    @Column(length = 200)
    @Comment("삼화작지 상단 메모")
    private String addMemo1;

    @Column(length = 200)
    @Comment("삼화작지 정장 이미지 바로 위 메모")
    private String addMemo2;

    @Column(length = 200)
    @Comment("삼화작지 정장 이미지 바로 오른쪽 메모")
    private String addMemo3;

    @Column(length = 200)
    @Comment("삼화작지 하의 이미지 바로 오른쪽 메모")
    private String addMemo4;

    @Column(length = 200)
    @Comment("삼화작지 하단 단추 텍스트")
    private String addMemo5;

    @Column(length = 800)
    @Comment("주문 하단 기타 요청사항")
    private String addMemo6;

    @Column(length = 800)
    @Comment("주문 하단 기타 요청사항")
    private String addMemo7;

    @Column(length = 800)
    @Comment("주문 하단 기타 요청사항")
    private String etcMemo;

    @Column(length = 400)
    @Comment("실색상")
    private String lineColor;

    @Column(columnDefinition = "decimal(3,1) default 0", nullable = false)
    @Comment("원단 폭")
    private BigDecimal fabricWidth;

    @Column(columnDefinition = "decimal(4,1) default 0", nullable = false)
    @Comment("원단길이")
    private BigDecimal fabricLength;

    @Column(columnDefinition = "decimal(3,1) default 0", nullable = false)
    @Comment("ST 폭")
    private BigDecimal stWidth;

    @Column(columnDefinition = "decimal(3,1) default 0", nullable = false)
    @Comment("# 폭")
    private BigDecimal sharpWidth;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean fabricLabel;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean brandLabel;

    public void updateMakingType(CommonCodeChild makingType) {
        this.makingType = makingType;
    }

    public void updatePicName(String picName) {
        this.picName = picName;
    }

    public void updateMeasurePic(String measurePic) {
        this.measurePic = measurePic;
    }

    public void updateHeight(BigDecimal height) {
        this.height = height;
    }

    public void updateWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public void updateAgeGroup(CommonCodeChild ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void updateSchedule(CommonCodeChild schedule) {
        this.schedule = schedule;
    }

    public void updateButton(CommonCodeChild button) {
        this.button = button;
    }

    public void updateLining(CommonCodeChild lining) {
        this.lining = lining;
    }

    public void updateFabricCompany(String fabricCompany) {
        this.fabricCompany = fabricCompany;
    }

    public void updateFabricPattern(String fabricPattern) {
        this.fabricPattern = fabricPattern;
    }

    public void updateFabricColor(String fabricColor) {
        this.fabricColor = fabricColor;
    }

    public void updateYard(BigDecimal yard) {
        this.yard = yard;
    }

    public void updateEtcMemo(String etcMemo) {
        this.etcMemo = etcMemo;
    }

    public void updateFabricWidth(BigDecimal fabricWidth) {
        this.fabricWidth = fabricWidth;
    }

    public void updateFabricLength(BigDecimal fabricLength) {
        this.fabricLength = fabricLength;
    }

    public void updateStWidth(BigDecimal stWidth) {
        this.stWidth = stWidth;
    }

    public void updateSharpWidth(BigDecimal sharpWidth) {
        this.sharpWidth = sharpWidth;
    }

    public void updateFabricLabel(Boolean fabricLabel) {
        this.fabricLabel = fabricLabel;
    }

    public void updateBrandLabel(Boolean brandLabel) {
        this.brandLabel = brandLabel;
    }

    public void updateAddMemo1(String addMemo1) {
        this.addMemo1 = addMemo1;
    }

    public void updateAddMemo2(String addMemo2) {
        this.addMemo2 = addMemo2;
    }

    public void updateAddMemo3(String addMemo3) {
        this.addMemo3 = addMemo3;
    }

    public void updateAddMemo4(String addMemo4) {
        this.addMemo4 = addMemo4;
    }

    public void updateAddMemo5(String addMemo5) {
        this.addMemo5 = addMemo5;
    }

    public void updateAddMemo6(String addMemo6) {
        this.addMemo6 = addMemo6;
    }

    public void updateAddMemo7(String addMemo7) {
        this.addMemo7 = addMemo7;
    }
}
