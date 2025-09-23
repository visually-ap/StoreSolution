package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResCodeListDTO {

    private String parentCodeValue;

    private String parentCodeName;

    private Long part;
    private Long type;

    private String codeName;

    private String codeValue;

    private Long id;

    private BigDecimal yard;

    private Integer showSequence;

    private Long fileId;
}
