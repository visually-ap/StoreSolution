package kr.co.apfactory.storesolution.domain.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private boolean isSuccess;

    private String message;

    private Object result;
}
