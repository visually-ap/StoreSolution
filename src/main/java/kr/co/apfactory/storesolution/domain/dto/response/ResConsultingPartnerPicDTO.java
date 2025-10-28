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
public class ResConsultingPartnerPicDTO {
    private Long partnerId;
    private Long picId;
    private String name;
    private String contact;
    private LocalDateTime insertDatetime;
}
