package kr.co.apfactory.storesolution.global.file.domain.repository.support;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.common.FileDTO;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import kr.co.apfactory.storesolution.global.file.domain.entity.QStoreFileAttach;
import kr.co.apfactory.storesolution.global.file.domain.entity.QStoreFileAttachMaster;
import lombok.RequiredArgsConstructor;

import java.util.List;

// querydsl 사용
@RequiredArgsConstructor
public class FileSupportRepositoryImpl implements FileSupportRepository {
    private final JPAQueryFactory queryFactory;

    private final SortManager sortManager;

    private final FilterManager filterManager;

    @Override
    public List<FileDTO> selectSiteImageList(Long storeId, int fileType) {
        QStoreFileAttach storeFileAttach = QStoreFileAttach.storeFileAttach;
        QStoreFileAttachMaster storeFileAttachMaster = QStoreFileAttachMaster.storeFileAttachMaster;

        List<FileDTO> results = queryFactory.select(
                        Projections.fields(
                                FileDTO.class
                                , storeFileAttach.id.as("fileId")
                                , storeFileAttachMaster.id.as("fileMasterId")
                                , storeFileAttach.originalFileName
                                , storeFileAttach.savedPathFile
                        )
                )
                .from(storeFileAttach)
                .innerJoin(storeFileAttachMaster).on(storeFileAttach.storeFileAttachMaster.eq(storeFileAttachMaster))
                .where(
                        storeFileAttachMaster.store.id.eq(storeId)
                                .and(storeFileAttachMaster.fileType.eq(fileType))
                )
                .orderBy(storeFileAttach.id.desc())
                .fetch();

        return results;
    }
}
