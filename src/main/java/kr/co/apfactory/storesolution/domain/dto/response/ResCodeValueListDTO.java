package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ResCodeValueListDTO {

    private String codeValue;

    private String codeName;

    private Long id;

    private BigDecimal yard;

    private Integer showSequence;

    private Long fileId;
}
