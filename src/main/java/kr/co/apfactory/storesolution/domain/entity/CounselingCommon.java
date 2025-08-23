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

    public void updateFabricData(ReqFabricSaveDTO reqFabricSaveDTO) {
        this.factory = reqFabricSaveDTO.getFactory();

        this.jacket = reqFabricSaveDTO.getJacket();
        this.pants = reqFabricSaveDTO.getPants();
        this.vest = reqFabricSaveDTO.getVest();
        this.coat = reqFabricSaveDTO.getCoat();

        if (this.jacket) {
            this.fabricCompanyJacket = reqFabricSaveDTO.getFabricCompanyJacket();
            this.fabricPatternJacket = reqFabricSaveDTO.getFabricPatternJacket();
            this.fabricColorJacket = reqFabricSaveDTO.getFabricColorJacket();
        } else {
            this.fabricCompanyJacket = null;
            this.fabricPatternJacket = null;
            this.fabricColorJacket = null;
        }

        if (this.pants) {
            this.fabricCompanyPants = reqFabricSaveDTO.getFabricCompanyPants();
            this.fabricPatternPants = reqFabricSaveDTO.getFabricPatternPants();
            this.fabricColorPants = reqFabricSaveDTO.getFabricColorPants();
        } else {
            this.fabricCompanyPants = null;
            this.fabricPatternPants = null;
            this.fabricColorPants = null;
        }

        if (this.vest) {
            this.fabricCompanyVest = reqFabricSaveDTO.getFabricCompanyVest();
            this.fabricPatternVest = reqFabricSaveDTO.getFabricPatternVest();
            this.fabricColorVest = reqFabricSaveDTO.getFabricColorVest();
        } else {
            this.fabricCompanyVest = null;
            this.fabricPatternVest = null;
            this.fabricColorVest = null;
        }

        if (this.coat) {
            this.fabricCompanyCoat = reqFabricSaveDTO.getFabricCompanyCoat();
            this.fabricPatternCoat = reqFabricSaveDTO.getFabricPatternCoat();
            this.fabricColorCoat = reqFabricSaveDTO.getFabricColorCoat();
        } else {
            this.fabricCompanyCoat = null;
            this.fabricPatternCoat = null;
            this.fabricColorCoat = null;
        }
    }
}
