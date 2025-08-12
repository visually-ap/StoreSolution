package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttachMaster;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_order_common")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class OrderCommon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("주문 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("원 주문 공통 시퀀스")
    private OrderCommon rootOrderCommon;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("주문 매장")
    private Store store;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("취소 여부")
    private Boolean canceled;

    @Column(columnDefinition = "boolean default true", nullable = false)
    @Comment("마일리지 결재 완납 여부 (true:완납, false:납부대기")
    private Boolean payment;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("1차 검수 여부 (마일리지 완납 여부와 관계 없이 검수 가능)")
    private Boolean checked;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("최종 검수 여부 (마일리지 완납 여부와 관계 없이 검수 가능)")
    private Boolean checkedFinal;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("캐드 완성 여부 (마일리지 완납 여부와 관계 없이 검수 가능)")
    private Boolean checkedCad;

    @Column(columnDefinition = "tinyint", nullable = false)
    @Comment("주문 부위 (1:정장, 2:코트")
    private Integer orderParts;

    @Column(columnDefinition = "tinyint", nullable = false)
    @Comment("주문 구분 (1:주문, 2:가봉완성, 3:수선, 4:게이지복, 5:대여복")
    private Integer orderType;

    @Column(columnDefinition = "tinyint", nullable = false)
    @Comment("주문 위치 구분 (1:국내, 2:해외(인니)")
    private Integer nation;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("작업방식 (13:직봉, 14:초가봉, 15:중가봉, 16: 가봉완성, 17:가봉완성-초가봉, 18:가봉완성-중가봉, 19:수선, 29:가봉후직봉")
    private CodeType workType;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("지급 요청 (10:일반, 11:지급, 12:초지급)")
    private CodeType paymentsRequest;

    @Column(columnDefinition = "date")
    @Comment("출고요청일")
    private LocalDate releaseReqDate;

    @Column(columnDefinition = "date")
    @Comment("AP도착일 : 원단 입고 등록 시 입력")
    private LocalDate apArrivalDate;

    @Column(columnDefinition = "date")
    @Comment("AP주문 발주일 : 생산 요청 시작 일")
    private LocalDate orderingDate;

    @Column(columnDefinition = "date")
    @Comment("B원단 도착일")
    private LocalDate iniArrivalDate;

    @Column(columnDefinition = "date")
    @Comment("b생산 시작일")
    private LocalDate iniManufactureDate;

    @Column(columnDefinition = "date")
    @Comment("출고예정일")
    private LocalDate releasePlanDate;

    @Column(columnDefinition = "date")
    @Comment("w/b 출고일")
    private LocalDate releaseDate;

    @Column(columnDefinition = "date")
    @Comment("AP 입고일")
    private LocalDate storageDate;
    @Column(columnDefinition = "date")
    @Comment("AP 검품일")
    private LocalDate checkItemDate;
    @Column(columnDefinition = "date")
    @Comment("배송예정일")
    private LocalDate deliveryPlanDate;

    @Column(columnDefinition = "date")
    @Comment("택배 대기일")
    private LocalDate deliveryWaitDate;

    @Column(columnDefinition = "date")
    @Comment("택배출고일")
    private LocalDate deliveryReleaseDate;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("가봉 중 여부")
    private Boolean basting;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("가봉 완료 여부 : 인니 가봉 주문은 재가봉 안됨")
    private Boolean bastingComplete;

    @Column(length = 200)
    private String customerName;

    @Column(length = 200)
    private String customerMobile;

    @Column(columnDefinition = "int", nullable = false)
    @Comment("주문번호")
    private Integer orderNo;

    @Column(length = 10)
    @Comment("주문태그 (년+월+주문번호")
    private String orderTag;

    @Column(length = 400)
    private String qrCode;

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

    @OneToOne(fetch = FetchType.LAZY)
    private FileAttachMaster miniMarkerFileAttachMaster;

    @OneToOne(fetch = FetchType.LAZY)
    private FileAttachMaster cutFileAttachMaster;

    @OneToOne(fetch = FetchType.LAZY)
    private FileAttachMaster patternFileAttachMaster;

    @OneToOne(fetch = FetchType.LAZY)
    private FileAttachMaster etcFileAttachMaster;

    @Column(columnDefinition = "boolean default false")
    private Boolean barcode;  // 바코드 생성 여부

    @Column(length = 50, unique = true)
    private String jacketBarcode;

    @Column(length = 100, unique = true)
    private String jacketBarcodeImagePath;

    @Column(length = 50, unique = true)
    private String pantsBarcode;

    @Column(length = 100, unique = true)
    private String pantsBarcodeImagePath;

    @Column(length = 50, unique = true)
    private String vestBarcode;

    @Column(length = 100, unique = true)
    private String vestBarcodeImagePath;

    @Column(length = 50, unique = true)
    private String coatBarcode;

    @Column(length = 100, unique = true)
    private String coatBarcodeImagePath;

    @Column(length = 50, unique = true)
    private String iniOrderBarcode;

    @Column(length = 100, unique = true)
    private String iniOrderBarcodeImagePath;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean input;  // 투입 여부

    @OneToOne(fetch = FetchType.LAZY)
    private User inputUser; // 투입자

    @Column(columnDefinition = "datetime")
    private LocalDateTime inputDatetime;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("재가봉 여부")
    private Boolean reBasting;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("대체가봉 여부")
    private Boolean substituteBasting;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("수선 중 여부")
    private Boolean repairing;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("핸드 옵션 유무")
    private Boolean handOption;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("턱시도(공단) 옵션 유무")
    private Boolean tuxedoOption;

    @Column(columnDefinition = "int default 0", nullable = false)
    @Comment("가봉 후 직봉 카운트")
    private Integer dafCount;

    @Column(columnDefinition = "int default 0", nullable = false)
    @Comment("가봉 카운트")
    private Integer bastingCount;

    @Column(length = 100, unique = true)
    private String jacketBlackImagePath1;
    @Column(length = 100, unique = true)
    private String jacketBlackImagePath2;

    @Column(length = 100, unique = true)
    private String pantsBlackImagePath1;
    @Column(length = 100, unique = true)
    private String pantsBlackImagePath2;

    @Column(length = 100, unique = true)
    private String vestBlackImagePath1;
    @Column(length = 100, unique = true)
    private String vestBlackImagePath2;

    @Column(length = 100, unique = true)
    private String coatBlackImagePath1;
    @Column(length = 100, unique = true)
    private String coatBlackImagePath2;
}
