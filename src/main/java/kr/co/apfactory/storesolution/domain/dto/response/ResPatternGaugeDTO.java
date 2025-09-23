package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResPatternGaugeDTO {
    private Long id;

    @Comment("사이즈 구분")
    private Integer sizeType;

    @Comment("상의,코트:상동")
    private BigDecimal data01;

    @Comment("상의,코트:상앞품")
    private BigDecimal data02;

    @Comment("상의,코트:상뒷품")
    private BigDecimal data03;

    @Comment("상의,코트:중동")
    private BigDecimal data04;

    @Comment("상의,코트:중앞품")
    private BigDecimal data05;

    @Comment("상의,코트:중뒷품")
    private BigDecimal data06;

    @Comment("상의,코트:어깨")
    private BigDecimal data07;

    @Comment("상의,코트:소매(좌)")
    private BigDecimal data08;

    @Comment("상의,코트:소매(우)")
    private BigDecimal data09;

    @Comment("상의,코트:총장")
    private BigDecimal data10;

    @Comment("상의,코트:앞기장")
    private BigDecimal data11;

    @Comment("상의,코트:소매통상박")
    private BigDecimal data12;

    @Comment("상의,코트:소매통부리")
    private BigDecimal data13;

    @Comment("상의,코트:요척")
    private BigDecimal yard;

    @Comment("노출 순서")
    private Integer showSequence;

    @Comment("등록 일시")
    private LocalDateTime insertDatetime;

    @Comment("등록자 이름")
    private String insertUserName;

    private String patternName;

    private int type;
}
