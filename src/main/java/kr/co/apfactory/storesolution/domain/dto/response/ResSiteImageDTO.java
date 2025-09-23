package kr.co.apfactory.storesolution.domain.dto.response;

import kr.co.apfactory.storesolution.domain.dto.common.FileDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResSiteImageDTO {
    private List<FileDTO> homeImageList;
    private FileDTO logoImage;
    private List<FileDTO> consultingImageList;
}
