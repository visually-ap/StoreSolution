package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResConsultingPartnerListDTO {
    private Long partnerId;
    private String name;
    private Integer charge;
    private LocalDateTime insertDatetime;
}
