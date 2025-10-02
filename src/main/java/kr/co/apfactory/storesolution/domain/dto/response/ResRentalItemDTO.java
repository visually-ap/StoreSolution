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
public class ResRentalItemDTO {
    private Long rentalItemId;
    private String name;
    private Integer size;
    private String barcode;
    private Boolean renting;
    private String memo;
    private LocalDateTime insertDatetime;
}
