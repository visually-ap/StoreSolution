package kr.co.apfactory.storesolution.domain.dto.request;

import lombok.Data;

@Data
public class ReqPasswordUpdateDTO {
    private String nowPassword;
    private String password;
    private String confirmPassword;
}
