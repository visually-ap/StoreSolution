package kr.co.apfactory.storesolution.domain.dto.request;

import kr.co.apfactory.storesolution.domain.entity.Customer;
import kr.co.apfactory.storesolution.domain.entity.CustomerPurchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqCustomerPurchaseDTO {
    private Long customerId;
    private Long purchaseId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;
    private Integer purchaseType;
    private String purchaseMemo;
    private BigDecimal purchasePrice;

    public CustomerPurchase toCustomerPurchaseEntity(Customer customer, Integer charge) {
        return CustomerPurchase.builder()
                .customer(customer)
                .type(this.purchaseType)
                .purchaseMemo(this.purchaseMemo)
                .price(this.purchasePrice)
                .purchaseDate(this.purchaseDate)
                .charge(charge)
                .build();
    }

}
