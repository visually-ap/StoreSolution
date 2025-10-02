package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqRentalDTO;
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
@Table(name = "tb_rental")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class Rental extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("대여 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("대여 상품 시퀀스")
    private RentalItem rentalItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("대여 고객 시퀀스")
    private Customer customer;

    @Column(columnDefinition = "date")
    @Comment("대여일")
    private LocalDate fromDate;

    @Column(columnDefinition = "date")
    @Comment("반납일")
    private LocalDate toDate;

    @Column(columnDefinition = "date")
    @Comment("예상 반납일")
    private LocalDate requestDate;

    @Column(columnDefinition = "boolean default false")
    @Comment("대여중 여부")
    private Boolean renting;

    @Column(columnDefinition = "boolean default false")
    @Comment("삭제 여부")
    private Boolean deleted;

    @Column(columnDefinition = "tinyint default 0")
    @Comment("구분 (0:기타)")
    private Integer type;

    @Column(length = 1000)
    @Comment("메모")
    private String memo;

    public void update(ReqRentalDTO dto) {
        this.type = dto.getRentalType();
        this.fromDate = dto.getFromDate();
        this.requestDate = dto.getRequestDate();
        if (!this.rentalItem.getId().equals(dto.getRentalItemId())) {
            this.rentalItem = RentalItem.builder().id(dto.getRentalItemId()).build();
        }
        this.memo = dto.getRentalMemo();
    }

    public void updateRenting(Boolean renting) {
        this.renting = renting;
    }

    public void delete() {
        this.deleted = true;
    }

    public void back() {
        this.toDate = LocalDate.now();
        this.renting = false;
    }

    public void cancelBack() {
        this.toDate = null;
        this.renting = true;
    }

    public void updateToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
