package kr.co.apfactory.storesolution.global.file.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFilesDTO {
    private Long id;

    private List<MultipartFile> file;
}
