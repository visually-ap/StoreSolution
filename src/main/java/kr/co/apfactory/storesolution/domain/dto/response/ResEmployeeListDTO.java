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
public class ResEmployeeListDTO {
    private Long userId;
    private String loginId;
    private String name;
    private String mobileNumber;
    private String position;
    private LocalDateTime insertDatetime;
}
