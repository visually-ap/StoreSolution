package kr.co.apfactory.storesolution.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqEmployeeUpdateDTO {
    private Long userId;
    private String name;
    private String mobileNumber;
    private Long position;
}
