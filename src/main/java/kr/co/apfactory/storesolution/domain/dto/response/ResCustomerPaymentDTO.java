package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResCustomerPaymentDTO {
    private Long paymentId;
    private LocalDate paymentDate;
    private Integer type;
    private String pic;
    private Integer method;
    private BigDecimal amount;
    private BigDecimal outstanding;
    private String memo;
}
