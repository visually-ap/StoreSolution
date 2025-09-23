package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResDesignPantsDTO {
    private Long id;
    private Long counselingCommon;

    // 디자인
    private Long pantsPattern;
    private Long pantsBeltLoop;
    private Long pantsPocket;
    private Long pantsHemShape;
    private Long pantsHemThickness;
    private Long pantsFlap;
    private Long pantsFlapLength;
    private Long pantsCheckFabric;
    private Long pantsAddOption1;
    private Long pantsAddOption2;
    private Long pantsAddOption3;
}
