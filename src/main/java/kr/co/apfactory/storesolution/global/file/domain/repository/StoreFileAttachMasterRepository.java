package kr.co.apfactory.storesolution.global.file.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.Store;
import kr.co.apfactory.storesolution.global.file.domain.entity.StoreFileAttachMaster;
import kr.co.apfactory.storesolution.global.file.domain.repository.support.FileSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreFileAttachMasterRepository extends JpaRepository<StoreFileAttachMaster, Long>, FileSupportRepository {
    List<StoreFileAttachMaster> findAllByStore(Store store);

    StoreFileAttachMaster findByStoreAndFileType(Store store, Integer fileType);
}
