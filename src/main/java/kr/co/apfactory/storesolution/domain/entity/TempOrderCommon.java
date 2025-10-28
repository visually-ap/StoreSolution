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
@Table(name = "tb_temp_order_common")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class TempOrderCommon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("주문 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("주문 매장")
    private Store store;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("취소 여부")
    private Boolean canceled;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("발주 여부")
    private Boolean ordering;

    @Column(columnDefinition = "tinyint", nullable = false)
    @Comment("주문 부위 (1:정장, 2:코트")
    private Integer orderParts;

    @Column(columnDefinition = "tinyint", nullable = false)
    @Comment("주문 위치 구분 (1:국내, 2:해외(인니), 3:기타")
    private Integer nation;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("작업방식 (13:직봉, 14:초가봉, 15:중가봉")
    private CodeType workType;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("지급 요청 (10:일반, 11:지급, 12:초지급)")
    private CodeType paymentsRequest;

    @Column(columnDefinition = "date")
    @Comment("출고요청일")
    private LocalDate releaseReqDate;

    @Column(length = 200)
    private String customerName;

    @Column(length = 200)
    private String customerMobile;

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
    @Comment("카운셀링 공통 시퀀스")
    private CounselingCommon counselingCommon;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("주문 공통 시퀀스")
    private OrderCommon orderCommon;

    @Column(columnDefinition = "datetime")
    @Comment("발주일")
    private LocalDateTime orderingDatetime;

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("발주자")
    private User orderingUser;
}
