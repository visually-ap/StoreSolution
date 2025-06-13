package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResEmployeeDetailDTO {
    private Long userId;
    private String loginId;
    private String name;
    private String mobileNumber;
    private Long position;
    private String positionName; // 직책 이름
    private Boolean enabled;
    private LocalDateTime insertDatetime;
}
