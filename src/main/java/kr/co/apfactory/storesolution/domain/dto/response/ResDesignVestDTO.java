package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResDesignVestDTO {
    private Long id;
    private Long counselingCommon;

    // 디자인
    private Long vestPattern;
    private Long vestBack;
    private Long vestFrontPocket;
    private Long vestChestPocket;
    private Long vestLapel;
    private Long vestAmf;
}
