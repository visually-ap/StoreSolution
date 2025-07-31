package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResCustomerDTO {
    private Long customerId;

    private String name1;
    private String mobile1;

    private String name2;
    private String mobile2;

    private LocalDate photoDate;
    private String photoPlace;

    private LocalDate weddingDate;
    private String weddingPlace;
    private Long reservationManagerId;
    private Long consultingManagerId;

    private LocalDate consultingDate;
    private String consultingHour;
    private String consultingMinute;

    private String memo;

    private Boolean completed;
}
