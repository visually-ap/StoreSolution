package kr.co.apfactory.storesolution.global.file.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultDTO implements Serializable {
    private String originalFileName;

    private String fileName;

    private String uuid;

    private String folderPath;

    public String getSavedPathFile() {
        return folderPath + "/" + fileName;
    }
}
