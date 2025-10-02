package kr.co.apfactory.storesolution.domain.dto.request;

import kr.co.apfactory.storesolution.domain.entity.ConsultingPartner;
import kr.co.apfactory.storesolution.domain.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqConsultingPartnerRegisterDTO {
    private String name;
    private String pic;
    private String contact;
    private Integer charge;
    private String memo;

    public ConsultingPartner toConsultingPartnerEntity() {
        return ConsultingPartner.builder()
                .name(this.name)
                .pic(this.pic)
                .contact(this.contact)
                .charge(this.charge)
                .memo(this.memo)
                .build();
    }
}
