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
public class ResRentalListDTO {
    private Long rentalId;
    private Long rentalItemId;
    private String rentalItemName;
    private Integer type;
    private String typeString;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate requestDate;
    private String name;
    private String memo;
    private Boolean renting;
    private String rentingState;

    public void setRentingState() {
        if (fromDate != null && toDate == null && !renting) {
            this.rentingState = "대여예약중";
        } else if (fromDate != null && toDate == null && renting) {
            this.rentingState = "대여중";
        } else if (fromDate != null && toDate != null && !renting) {
            this.rentingState = "반납완료";
        } else {
            this.rentingState = "대여가능";
        }
    }
}
