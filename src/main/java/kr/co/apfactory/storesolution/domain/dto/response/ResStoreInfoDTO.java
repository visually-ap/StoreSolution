package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResStoreInfoDTO {
    private Long id;
    private String storeName;
    private String businessRegistrationNumber;
    private String ceoName;
    private String address;

    // 사업자등록 정보
    private String buyerCompanyName;
    private String buyerCeoName;
    private String buyerBusinessType;
    private String buyerBusinessItem;
    private String buyerEmail;
}
