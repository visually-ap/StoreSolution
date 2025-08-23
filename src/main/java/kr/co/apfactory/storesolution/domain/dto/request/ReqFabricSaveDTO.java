package kr.co.apfactory.storesolution.domain.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ReqFabricSaveDTO {
    private Long reservationId;

    private Integer factory;

    private Boolean jacket = false;
    private Boolean pants = false;
    private Boolean vest = false;
    private Boolean coat = false;

    private String fabricCompanyJacket;
    private String fabricPatternJacket;
    private String fabricColorJacket;

    private String fabricCompanyPants;
    private String fabricPatternPants;
    private String fabricColorPants;

    private String fabricCompanyVest;
    private String fabricPatternVest;
    private String fabricColorVest;

    private String fabricCompanyCoat;
    private String fabricPatternCoat;
    private String fabricColorCoat;
}
