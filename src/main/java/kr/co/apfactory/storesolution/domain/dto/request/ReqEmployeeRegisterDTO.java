package kr.co.apfactory.storesolution.domain.dto.request;

import lombok.Data;

@Data
public class ReqEmployeeRegisterDTO {
    private String loginId;
    private String password;
    private String confirmPassword;
    private String name;
    private String mobileNumber;
    private Long position;
}
