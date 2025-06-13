package kr.co.apfactory.storesolution.domain.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {
    private Long fileId;
    private Long fileMasterId;
    private String originalFileName;
    private String savedPathFile;
}
