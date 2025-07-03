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
public class ResReservationDTO {
    private Long id;
    private Boolean allDay;
    private LocalDateTime consultingDatetimeFrom;
    private LocalDateTime consultingDatetimeTo;
    private String customerName;
    private Integer type;
}
