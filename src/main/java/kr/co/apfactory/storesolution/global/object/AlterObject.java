package kr.co.apfactory.storesolution.global.object;

import kr.co.apfactory.storesolution.domain.dto.common.FileDTO;
import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttach;
import kr.co.apfactory.storesolution.global.file.domain.entity.StoreFileAttach;
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

    public FileDTO toFileDTO(StoreFileAttach file) {
        return FileDTO.builder()
                .fileId(file.getId())
                .fileMasterId(file.getStoreFileAttachMaster() != null ? file.getStoreFileAttachMaster().getId() : null)
                .originalFileName(file.getOriginalFileName())
                .savedPathFile(file.getSavedPathFile())
                .build();
    }
}
