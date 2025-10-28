package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqCustomerPurchaseDTO;
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
@Table(name = "tb_customer_purchase")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CustomerPurchase extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고객 구매 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("고객 시퀀스")
    private Customer customer;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부 (true : 삭제, false : 미삭제)")
    private Boolean deleted;

    @Column(columnDefinition = "tinyint default 0", nullable = false)
    @Comment("상품구분 (0:지정안함, 1:맞춤정장)")
    private Integer type;

    @Column(length = 50, nullable = false)
    @Comment("구매내역")
    private String purchaseMemo;

    @Column(columnDefinition = "decimal(8,0) default 0", nullable = false)
    @Comment("구매금액")
    private BigDecimal price;

    @Column(columnDefinition = "date default now()", nullable = false)
    @Comment("구매일")
    private LocalDate purchaseDate;

    @Column(columnDefinition = "tinyint default 0", nullable = false)
    @Comment("수수료율")
    private Integer charge;

    public void update(ReqCustomerPurchaseDTO dto) {
        this.type = dto.getPurchaseType();
        this.purchaseMemo = dto.getPurchaseMemo();
        this.price = dto.getPurchasePrice();
        this.purchaseDate = dto.getPurchaseDate();
    }

    public void delete() {
        this.deleted = true;
    }
}
