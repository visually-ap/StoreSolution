package kr.co.apfactory.storesolution.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResFileAttachDTO {
    private Long id;

    private String originalFileName;

    private String savedPathFile;

    private Integer downloadCount;
}
