package kr.co.apfactory.storesolution.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodePartEnum {
    SUIT(1L)
    , COAT(2L)
    ;
    private Long value;

    public static CodePartEnum valueOf(long value) {
        for (CodePartEnum theEnum : CodePartEnum.values()) {
            if (theEnum.value == value) {
                return theEnum;
            }
        }

        return null;
    }
}
