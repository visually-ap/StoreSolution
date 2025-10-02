package kr.co.apfactory.storesolution.domain.dto.request;

import kr.co.apfactory.storesolution.domain.entity.Customer;
import kr.co.apfactory.storesolution.domain.entity.Rental;
import kr.co.apfactory.storesolution.domain.entity.RentalItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqRentalDTO {
    private Long customerId;
    private Long rentalId;
    private Integer rentalType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
    private Long rentalItemId;
    private String rentalMemo;

    public Rental toRentalEntity() {
        return Rental.builder()
                .rentalItem(RentalItem.builder().id(this.rentalItemId).build())
                .customer(Customer.builder().id(this.customerId).build())
                .fromDate(this.fromDate)
                .requestDate(this.requestDate)
                .type(this.rentalType)
                .memo(this.rentalMemo)
                .build();
    }
}
