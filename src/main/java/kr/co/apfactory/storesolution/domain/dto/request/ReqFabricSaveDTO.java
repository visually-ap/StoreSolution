package kr.co.apfactory.storesolution.domain.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ReqFabricSaveDTO {
    private Long customerId;

    private Integer factory;

    private Boolean jacket;
    private Boolean pants;
    private Boolean vest;
    private Boolean coat;

    private String fabricCompany;
    private String fabricPattern;
    private String fabricColor;
}
