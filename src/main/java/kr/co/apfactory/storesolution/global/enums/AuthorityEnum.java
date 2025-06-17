package kr.co.apfactory.storesolution.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthorityEnum {
    ADMIN(1, "ROLE_ADMIN")
    , MANAGER(2, "ROLE_MANAGER")
    , USER(3, "ROLE_USER")
    , EMPLOYEE(4, "ROLE_EMPLOYEE")
    , OFFICER(5, "ROLE_OFFICER");

    private int value;
    private String auth;
}
