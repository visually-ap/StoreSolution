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
public class ResCustomerReservationDTO {
    private Long reservationId;
    private Boolean allDay;
    private Integer type;
    private String typeString;
    private Long consultingManagerId;
    private String consultingManagerName;
    private LocalDate consultingDate;
    private LocalDateTime consultingDatetimeFrom;
    private LocalDateTime consultingDatetimeTo;
    private Boolean completed;
    private Integer contract;
    private String contractString;
    private String memo;
    private String consultingHour;
    private String consultingMinute;

    public void setConsultingDatetime() {
        if (!this.allDay) {
            this.consultingHour = String.format("%02d", this.consultingDatetimeFrom.getHour());
            this.consultingMinute = String.format("%02d", this.consultingDatetimeFrom.getMinute());
        } else {
            this.consultingHour = "";
            this.consultingMinute = "";
        }
    }
}
