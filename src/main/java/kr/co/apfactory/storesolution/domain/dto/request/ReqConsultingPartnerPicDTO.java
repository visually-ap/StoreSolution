package kr.co.apfactory.storesolution.domain.dto.request;

import kr.co.apfactory.storesolution.domain.entity.ConsultingPartner;
import kr.co.apfactory.storesolution.domain.entity.ConsultingPartnerPic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqConsultingPartnerPicDTO {
    private Long partnerId;
    private String name;
    private String contact;

    public ConsultingPartnerPic toConsultingPartnerPicEntity() {
        return ConsultingPartnerPic.builder()
                .consultingPartner(ConsultingPartner.builder().id(this.partnerId).build())
                .name(this.name)
                .contact(this.contact)
                .build();
    }
}
