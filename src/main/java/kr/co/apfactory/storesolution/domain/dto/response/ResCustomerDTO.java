package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResCustomerDTO {
    private Long customerId;
    private Long reservationId;

    private String name1;
    private String mobile1;

    private String name2;
    private String mobile2;

    private LocalDate photoDate;
    private String photoPlace;

    private LocalDate weddingDate;
    private String weddingPlace;
    private Long reservationManagerId;
    private String reservationManagerName;
    private Long consultingManagerId;
    private String consultingManagerName;

    private LocalDate consultingDate;
    private LocalDateTime consultingDatetimeFrom;
    private String consultingHour;
    private String consultingMinute;

    private String memo;

    private Boolean completed;

    private String insertDate;
    private String reservationTypeString;
    private Integer type;
    private Integer contract;

    private Boolean isAllday;

    private Long consultingPartnerId;
    private String consultingPartnerName;

    public void setConsultingDatetime() {
        if (!this.isAllday) {
            this.consultingHour = String.format("%02d", this.consultingDatetimeFrom.getHour());
            this.consultingMinute = String.format("%02d", this.consultingDatetimeFrom.getMinute());
        } else {
            this.consultingHour = "";
            this.consultingMinute = "";
        }
    }
}
