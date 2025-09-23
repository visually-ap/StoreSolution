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
import java.time.LocalDate;

@Entity
@Table(name = "tb_customer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고객 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("매장 시퀀스")
    private Store store;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부 (true : 삭제, false : 미삭제)")
    private Boolean deleted;

    @Column(length = 50, nullable = false)
    @Comment("고객명1")
    private String name1;

    @Column(length = 50, nullable = false)
    @Comment("휴대전화번호1")
    private String mobile1;

    @Column(length = 50)
    @Comment("고객명2")
    private String name2;

    @Column(length = 50)
    @Comment("휴대전화번호2")
    private String mobile2;

    @Column(columnDefinition = "date")
    @Comment("촬영일")
    private LocalDate photoDate;

    @Column(length = 50)
    @Comment("촬영장소")
    private String photoPlace;

    @Column(columnDefinition = "date")
    @Comment("본식일")
    private LocalDate weddingDate;

    @Column(length = 50)
    @Comment("촬영장소")
    private String weddingPlace;

    @Column(length = 1000)
    @Comment("메모")
    private String memo;

    public void updateStore(Store store) {
        this.store = store;
    }

    public void updateCustomerInfo(ReqReservationUpdateDTO dto) {
        this.name1 = dto.getName1();
        this.mobile1 = dto.getMobile1();
        this.name2 = dto.getName2();
        this.mobile2 = dto.getMobile2();
        this.photoDate = dto.getPhotoDate();
        this.photoPlace = dto.getPhotoPlace();
        this.weddingDate = dto.getWeddingDate();
        this.weddingPlace = dto.getWeddingPlace();
        this.memo = dto.getMemo();
    }
}
