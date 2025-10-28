package kr.co.apfactory.storesolution.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqConsultingPartnerUpdateDTO {
    private Long partnerId;
    private String name;
    private Integer charge;
    private String memo;
}
