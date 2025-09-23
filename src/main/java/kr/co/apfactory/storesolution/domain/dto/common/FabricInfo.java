package kr.co.apfactory.storesolution.domain.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class FabricInfo {
    private String part;    // 상의, 하의, 조끼
    private String company; // 원단 회사
    private String number;  // 원단 품번
    private String color;   // 원단 색상
}
