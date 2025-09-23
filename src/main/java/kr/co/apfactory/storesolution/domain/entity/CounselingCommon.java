package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqFabricSaveDTO;
import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttachMaster;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_counseling_common")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CounselingCommon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("카운셀링 공통 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("주문 매장")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("예약 시퀀스")
    private Reservation reservation;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("취소 여부")
    private Boolean canceled;

    @Column(columnDefinition = "tinyint")
    @Comment("주문 위치 구분 (1:화이트, 2:블랙, 3:기타")
    private Integer factory;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("작업방식 (13:직봉, 14:초가봉, 15:중가봉")
    private CodeType workType;

    @Column(columnDefinition = "date")
    @Comment("출고요청일")
    private LocalDate releaseReqDate;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean jacket;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean pants;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean vest;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean coat;

    @OneToOne(fetch = FetchType.LAZY)
    private FileAttachMaster fileAttachMaster;

    @Column(length = 100)
    @Comment("상의, 코트 원단 회사")
    private String fabricCompanyJacket;

    @Column(length = 100)
    @Comment("상의, 코트 원단 품번")
    private String fabricPatternJacket;

    @Column(length = 100)
    @Comment("상의, 코트 원단 색상")
    private String fabricColorJacket;

    @Column(length = 100)
    @Comment("하의 원단 회사")
    private String fabricCompanyPants;

    @Column(length = 100)
    @Comment("하의 원단 품번")
    private String fabricPatternPants;

    @Column(length = 100)
    @Comment("하의 원단 색상")
    private String fabricColorPants;

    @Column(length = 100)
    @Comment("조끼 원단 회사")
    private String fabricCompanyVest;

    @Column(length = 100)
    @Comment("조끼 원단 품번")
    private String fabricPatternVest;

    @Column(length = 100)
    @Comment("조끼 원단 색상")
    private String fabricColorVest;

    @Column(length = 100)
    @Comment("조끼 원단 회사")
    private String fabricCompanyCoat;

    @Column(length = 100)
    @Comment("조끼 원단 품번")
    private String fabricPatternCoat;

    @Column(length = 100)
    @Comment("조끼 원단 색상")
    private String fabricColorCoat;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean allSameFabric;

    public void updateFabricData(ReqFabricSaveDTO dto) {
        this.workType = CodeType.builder().id(dto.getWorkType()).build();
        this.factory = dto.getFactory();

        this.jacket = dto.getJacket();
        this.pants = dto.getPants();
        this.vest = dto.getVest();
        this.coat = dto.getCoat();

        this.allSameFabric = dto.getAllSameFabric();

        if (this.jacket) {
            this.fabricCompanyJacket = dto.getFabricCompanyJacket();
            this.fabricPatternJacket = dto.getFabricPatternJacket();
            this.fabricColorJacket = dto.getFabricColorJacket();
        } else {
            this.fabricCompanyJacket = null;
            this.fabricPatternJacket = null;
            this.fabricColorJacket = null;
        }

        if (this.pants) {
            this.fabricCompanyPants = dto.getFabricCompanyPants();
            this.fabricPatternPants = dto.getFabricPatternPants();
            this.fabricColorPants = dto.getFabricColorPants();
        } else {
            this.fabricCompanyPants = null;
            this.fabricPatternPants = null;
            this.fabricColorPants = null;
        }

        if (this.vest) {
            this.fabricCompanyVest = dto.getFabricCompanyVest();
            this.fabricPatternVest = dto.getFabricPatternVest();
            this.fabricColorVest = dto.getFabricColorVest();
        } else {
            this.fabricCompanyVest = null;
            this.fabricPatternVest = null;
            this.fabricColorVest = null;
        }

        if (this.coat) {
            this.fabricCompanyCoat = dto.getFabricCompanyCoat();
            this.fabricPatternCoat = dto.getFabricPatternCoat();
            this.fabricColorCoat = dto.getFabricColorCoat();
        } else {
            this.fabricCompanyCoat = null;
            this.fabricPatternCoat = null;
            this.fabricColorCoat = null;
        }
    }

    public String getFabricCompanyFromCounseling() {
        if (this.getJacket()) {
            return this.getFabricCompanyJacket();
        } else if (this.getPants()) {
            return this.getFabricCompanyPants();
        } else if (this.getVest()) {
            return this.getFabricCompanyVest();
        } else if (this.getCoat()) {
            return this.getFabricCompanyCoat();
        } else {
            return null;
        }
    }

    public String getFabricPatternFromCounseling() {
        if (this.getJacket()) {
            return this.getFabricPatternJacket();
        } else if (this.getPants()) {
            return this.getFabricPatternPants();
        } else if (this.getVest()) {
            return this.getFabricPatternVest();
        } else if (this.getCoat()) {
            return this.getFabricPatternCoat();
        } else {
            return null;
        }
    }

    public String getFabricColorFromCounseling() {
        if (this.getJacket()) {
            return this.getFabricColorJacket();
        } else if (this.getPants()) {
            return this.getFabricColorPants();
        } else if (this.getVest()) {
            return this.getFabricColorVest();
        } else if (this.getCoat()) {
            return this.getFabricColorCoat();
        } else {
            return null;
        }
    }

    private String makeFabricKey(String company, String pattern, String color) {
        if (company == null || pattern == null || color == null) {
            return null;
        }
        return company + "_" + pattern + "_" + color;
    }

    public List<String> extractFabricKeys() {
        List<String> keys = new ArrayList<>();

        if (Boolean.TRUE.equals(this.jacket)) {
            keys.add(makeFabricKey(fabricCompanyJacket, fabricPatternJacket, fabricColorJacket));
        }
        if (Boolean.TRUE.equals(this.pants)) {
            keys.add(makeFabricKey(fabricCompanyPants, fabricPatternPants, fabricColorPants));
        }
        if (Boolean.TRUE.equals(this.vest)) {
            keys.add(makeFabricKey(fabricCompanyVest, fabricPatternVest, fabricColorVest));
        }
        if (Boolean.TRUE.equals(this.coat)) {
            keys.add(makeFabricKey(fabricCompanyCoat, fabricPatternCoat, fabricColorCoat));
        }

        return keys.stream()
                .filter(Objects::nonNull)   // null 제외
                .distinct()                 // 중복 제거
                .collect(Collectors.toList());
    }
}
