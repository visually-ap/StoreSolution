package kr.co.apfactory.storesolution.domain.entity;

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

    @Column(columnDefinition = "tinyint", nullable = false)
    @Comment("주문 위치 구분 (1:화이트, 2:블랙, 3:기타")
    private Integer factory;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("작업방식 (13:직봉, 14:초가봉, 15:중가봉")
    private CodeType workType;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("지급 요청 (10:일반, 11:지급, 12:초지급)")
    private CodeType paymentsRequest;

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
}
