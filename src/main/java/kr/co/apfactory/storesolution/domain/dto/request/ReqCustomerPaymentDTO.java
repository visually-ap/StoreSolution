package kr.co.apfactory.storesolution.domain.dto.request;

import kr.co.apfactory.storesolution.domain.entity.Customer;
import kr.co.apfactory.storesolution.domain.entity.CustomerPayment;
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
public class ReqCustomerPaymentDTO {
    private Long customerId;
    private Long paymentId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    private Integer paymentType;
    private String paymentPic;
    private Integer paymentMethod;
    private BigDecimal paymentAmount;
    private BigDecimal paymentOutstanding;
    private String paymentMemo;

    public CustomerPayment toCustomerPaymentEntity(Customer customer) {
        return CustomerPayment.builder()
                .customer(customer)
                .paymentDate(this.paymentDate)
                .type(this.paymentType)
                .pic(this.paymentPic)
                .method(this.paymentMethod)
                .amount(this.paymentAmount)
                .outstanding(this.paymentOutstanding)
                .memo(this.paymentMemo)
                .build();
    }
}
