package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResCustomerPurchaseListDTO {
    private Long purchaseId;
    private String typeString;
    private Integer type;
    private String purchaseMemo;
    private BigDecimal price;
    private Integer charge;
    private LocalDate purchaseDate;
    private Integer chargeFee;
}
