package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqRentalItemDTO;
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
import java.time.LocalDate;

@Entity
@Table(name = "tb_rental_item")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class RentalItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("대여 제품 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("매장 시퀀스")
    private Store store;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부 (true : 삭제, false : 미삭제)")
    private Boolean deleted;

    @Column(length = 50, nullable = false)
    @Comment("상품명")
    private String name;

    @Column(columnDefinition = "int default 0")
    @Comment("사이즈")
    private Integer size;

    @Column(length = 50)
    @Comment("바코드")
    private String barcode;

    @Column(columnDefinition = "boolean default false")
    @Comment("대여중 여부")
    private Boolean renting;

    @Column(length = 1000)
    @Comment("메모")
    private String memo;

    public void updateStore(Store store) {
        this.store = store;
    }

    public void delete() {
        this.deleted = true;
    }

    public void update(ReqRentalItemDTO dto) {
        this.name = dto.getName();
        this.size = dto.getSize();
        this.memo = dto.getMemo();
    }

    public void updateRenting(Boolean renting) {
        this.renting = renting;
    }
}
