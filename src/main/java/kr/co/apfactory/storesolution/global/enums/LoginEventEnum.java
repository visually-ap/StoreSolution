package kr.co.apfactory.storesolution.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginEventEnum {
    LOGIN(1, "로그인")
    , LOGOUT(2, "로그아웃");

    private int value;

    private String event;

    public static LoginEventEnum valueOf(int value) {
        for (LoginEventEnum theEnum : LoginEventEnum.values()) {
            if (theEnum.value == value) {
                return theEnum;
            }
        }

        return null;
    }
}
