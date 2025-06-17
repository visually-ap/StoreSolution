package kr.co.apfactory.storesolution.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ParentCodeTypeEnum {
    COMMON("공통", "Common", "Common", "Common", "Common", 1)
    , JACKET("상의", "Jacket", "Jacket", "Jacket", "Jacket", 2)
    , PANTS("하의", "Pants", "Pants", "Pants", "Pants", 3)
    , VEST("조끼", "Vest", "Vest", "Vest", "Vest", 4)
    , COAT("코트", "Coat", "Coat", "Coat", "Coat", 5)
    ;

    private String typeNameKo;
    private String typeNameEn;
    private String typeNameCn;
    private String typeNameJp;
    private String typeNameIn;
    private Integer value;

    public static ParentCodeTypeEnum valueOf(long value) {
        for (ParentCodeTypeEnum theEnum : ParentCodeTypeEnum.values()) {
            if (theEnum.value == value) {
                return theEnum;
            }
        }

        return null;
    }
}
