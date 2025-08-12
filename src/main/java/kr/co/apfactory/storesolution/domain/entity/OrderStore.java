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
@Table(name = "tb_order_store")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class OrderStore extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("매장 주문 솔루션 주문 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("주문 매장")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("고객")
    private Customer customer;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("취소 여부")
    private Boolean canceled;

    @Column(columnDefinition = "tinyint", nullable = false)
    @Comment("주문 위치 구분 (1:화이트, 2:블랙")
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
    @Comment("원단 회사")
    private String fabricCompany;

    @Column(length = 100)
    @Comment("원단 품번")
    private String fabricPattern;

    @Column(length = 100)
    @Comment("원단 색상")
    private String fabricColor;
}
