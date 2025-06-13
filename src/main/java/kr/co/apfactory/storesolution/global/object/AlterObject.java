package kr.co.apfactory.storesolution.global.object;

import kr.co.apfactory.storesolution.domain.dto.common.FileDTO;
import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttach;
import org.springframework.stereotype.Component;

@Component
public class AlterObject {
    public FileDTO toFileDTO(FileAttach file) {
        return FileDTO.builder()
                .fileId(file.getId())
                .fileMasterId(file.getFileAttachMaster() != null ? file.getFileAttachMaster().getId() : null)
                .originalFileName(file.getOriginalFileName())
                .savedPathFile(file.getSavedPathFile())
                .build();
    }
}
