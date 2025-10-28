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
public class ResStatDTO {
    private Long reservationType1;
    private Long reservationType2;
    private Long reservationType3;
    private Long reservationType4;
    private Long reservationType5;
    private Long reservationType6;

    private Long contractType0;
    private Long contractType1;
    private Long contractType2;
    private Long contractType3;
}
