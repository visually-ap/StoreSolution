package kr.co.apfactory.storesolution.global.file.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DownloadFileDTO {
    private Long id;
    private String uploadedPathFile;
    private String filename;
    private boolean isImage;
}
