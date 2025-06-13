package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResMypageDTO {
    private Long id;
    private String loginId;
    private String name;
    private String mobileNumber;
    private String position;
}
