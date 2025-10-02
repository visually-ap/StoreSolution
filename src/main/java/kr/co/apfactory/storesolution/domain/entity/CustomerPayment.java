package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqCustomerPaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_customer_payment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CustomerPayment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고객 결제 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("고객 시퀀스")
    private Customer customer;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부 (true : 삭제, false : 미삭제)")
    private Boolean deleted;

    @Column(columnDefinition = "date default now()", nullable = false)
    @Comment("결제일")
    private LocalDate paymentDate;

    @Column(columnDefinition = "tinyint default 0", nullable = false)
    @Comment("결제구분 (0:기타, 1:선금, 2:중도금, 3:잔금)")
    private Integer type;

    @Column(length = 50, nullable = false)
    @Comment("담당자")
    private String pic;

    @Column(columnDefinition = "tinyint default 0", nullable = false)
    @Comment("결제 방식 (0:기타, 1:카드, 2:계좌이체, 3:현금)")
    private Integer method;

    @Column(columnDefinition = "decimal(8,0) default 0", nullable = false)
    @Comment("금액")
    private BigDecimal amount;

    @Column(columnDefinition = "decimal(8,0) default 0", nullable = false)
    @Comment("미수금")
    private BigDecimal outstanding;

    @Column(length = 1000)
    @Comment("비고")
    private String memo;

    public void update(ReqCustomerPaymentDTO dto) {
        this.paymentDate = dto.getPaymentDate();
        this.type = dto.getPaymentType();
        this.pic = dto.getPaymentPic();
        this.method = dto.getPaymentMethod();
        this.amount = dto.getPaymentAmount();
        this.outstanding = dto.getPaymentOutstanding();
        this.memo = dto.getPaymentMemo();
    }

    public void delete() {
        this.deleted = true;
    }
}
