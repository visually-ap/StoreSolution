package kr.co.apfactory.storesolution.domain.dto.response;

import kr.co.apfactory.storesolution.domain.dto.request.ReqFabricSaveDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResCounselingDTO {
    private Long customerId;
    private Long orderConsultingId;
    private Long reservationId;

    private Integer factory;

    private String name1;
    private String mobile1;

    private Boolean jacket = false;
    private Boolean pants = false;
    private Boolean vest = false;
    private Boolean coat = false;

    private String fabricCompany;
    private String fabricPattern;
    private String fabricColor;

    public void updateFabricData(ReqFabricSaveDTO reqFabricSaveDTO){
        this.factory = reqFabricSaveDTO.getFactory();

        this.jacket = reqFabricSaveDTO.getJacket();
        this.pants = reqFabricSaveDTO.getPants();
        this.vest = reqFabricSaveDTO.getVest();
        this.coat = reqFabricSaveDTO.getCoat();

        this.fabricCompany = reqFabricSaveDTO.getFabricCompany();
        this.fabricPattern = reqFabricSaveDTO.getFabricPattern();
        this.fabricColor = reqFabricSaveDTO.getFabricColor();
    }
}
