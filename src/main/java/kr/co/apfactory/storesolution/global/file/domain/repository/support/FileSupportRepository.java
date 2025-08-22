package kr.co.apfactory.storesolution.global.file.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.common.FileDTO;

import java.util.List;

public interface FileSupportRepository {
    List<FileDTO> selectSiteImageList(Long storeId, int fileType);
}
