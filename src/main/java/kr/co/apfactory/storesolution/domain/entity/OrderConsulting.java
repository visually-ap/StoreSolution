package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqReservationUpdateDTO;
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
@Table(name = "tb_order_consulting")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class OrderConsulting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("주문 상담(구매를 위한 업무 프로세스 그룹) 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("고객 시퀀스")
    private Customer customer;

    @Column(length = 5)
    @Comment("AP 주문번호")
    private String orderNo;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부 (true : 삭제, false : 미삭제)")
    private Boolean deleted;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("완료 여부 (true : 삭제, false : 미삭제)")
    private Boolean completed;

    @Column(columnDefinition = "decimal(8,0) default 0", nullable = false)
    @Comment("총 금액")
    private BigDecimal totalPrice;

    @Column(columnDefinition = "decimal(8,0) default 0", nullable = false)
    @Comment("계약금, 선금")
    private BigDecimal deposit;

    @Column(columnDefinition = "decimal(8,0) default 0", nullable = false)
    @Comment("할인액")
    private BigDecimal discount;

    public void updateCustomer(Customer customer) {
        this.customer = customer;
    }

    public void updateOrderConsultingInfo(ReqReservationUpdateDTO dto) {

    }
}
