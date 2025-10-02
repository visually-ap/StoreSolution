package kr.co.apfactory.storesolution.domain.dto.response;

import kr.co.apfactory.storesolution.domain.dto.request.ReqFabricSaveDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResCounselingDTO {
    private Long customerId;
    private Long reservationId;
    private Long counselingCommonId;
    private Long orderCommonId;

    private String name;
    private String mobile;

    private Long workType;
    private Integer factory;

    private Boolean jacket = false;
    private Boolean pants = false;
    private Boolean vest = false;
    private Boolean coat = false;
    private Boolean allSameFabric = false;

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

    private Boolean saved;
    private LocalDate orderingDate;
    private String fabricInfo;


    public void updateFabricData(ReqFabricSaveDTO reqFabricSaveDTO){
        this.factory = reqFabricSaveDTO.getFactory();

        this.jacket = reqFabricSaveDTO.getJacket();
        this.pants = reqFabricSaveDTO.getPants();
        this.vest = reqFabricSaveDTO.getVest();
        this.coat = reqFabricSaveDTO.getCoat();

        this.fabricCompanyJacket = reqFabricSaveDTO.getFabricCompanyJacket();
        this.fabricPatternJacket = reqFabricSaveDTO.getFabricPatternJacket();
        this.fabricColorJacket = reqFabricSaveDTO.getFabricColorJacket();

        this.fabricCompanyPants = reqFabricSaveDTO.getFabricCompanyPants();
        this.fabricPatternPants = reqFabricSaveDTO.getFabricPatternPants();
        this.fabricColorPants = reqFabricSaveDTO.getFabricColorPants();

        this.fabricCompanyVest = reqFabricSaveDTO.getFabricCompanyVest();
        this.fabricPatternVest = reqFabricSaveDTO.getFabricPatternVest();
        this.fabricColorVest = reqFabricSaveDTO.getFabricColorVest();

        this.fabricCompanyCoat = reqFabricSaveDTO.getFabricCompanyCoat();
        this.fabricPatternCoat = reqFabricSaveDTO.getFabricPatternCoat();
        this.fabricColorCoat = reqFabricSaveDTO.getFabricColorCoat();
    }

    public void setFabricInfo() {
        if (this.allSameFabric) {
            if (this.jacket) {
                this.fabricInfo = this.fabricCompanyJacket + " " + this.fabricPatternJacket + " " + this.fabricColorJacket;
            } else if (this.pants) {
                this.fabricInfo = this.fabricCompanyPants + " " + this.fabricPatternPants + " " + this.fabricColorPants;
            } else if (this.vest) {
                this.fabricInfo = this.fabricCompanyVest + " " + this.fabricPatternVest + " " + this.fabricColorVest;
            } else if (this.coat) {
                this.fabricInfo = this.fabricCompanyCoat + " " + this.fabricPatternCoat + " " + this.fabricColorCoat;
            }
        } else {
            this.fabricInfo = "";
            if (this.jacket) {
                this.fabricInfo += this.fabricCompanyJacket + " " + this.fabricPatternJacket + " " + this.fabricColorJacket + "\n";
            }
            if (this.pants) {
                this.fabricInfo += this.fabricCompanyPants + " " + this.fabricPatternPants + " " + this.fabricColorPants + "\n";
            }
            if (this.vest) {
                this.fabricInfo += this.fabricCompanyVest + " " + this.fabricPatternVest + " " + this.fabricColorVest + "\n";
            }
            if (this.coat) {
                this.fabricInfo += this.fabricCompanyCoat + " " + this.fabricPatternCoat + " " + this.fabricColorCoat + "\n";
            }
            this.fabricInfo.trim();
            this.fabricInfo.replaceAll("\n", "<br>");
        }
    }
}
