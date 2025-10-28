package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqConsultingPartnerUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_consulting_partner")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class ConsultingPartner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("컨설팅 업체 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("매장 시퀀스")
    private Store store;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부 (true : 삭제, false : 미삭제)")
    private Boolean deleted;

    @Column(length = 50, nullable = false)
    @Comment("업체명")
    private String name;

    @Column(columnDefinition = "int default 0", nullable = false)
    @Comment("수수료율")
    private Integer charge;

    @Column(length = 1000)
    @Comment("메모")
    private String memo;

    public void updateStore(Store store) {
        this.store = store;
    }

    public void updateDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void updateConsultingPartner(ReqConsultingPartnerUpdateDTO dto) {
        this.name = dto.getName();
        this.charge = dto.getCharge();
        this.memo = dto.getMemo();
    }
}
