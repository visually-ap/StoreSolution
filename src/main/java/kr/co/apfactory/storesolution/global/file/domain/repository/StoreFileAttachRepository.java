package kr.co.apfactory.storesolution.global.file.domain.repository;

import kr.co.apfactory.storesolution.global.file.domain.entity.StoreFileAttach;
import kr.co.apfactory.storesolution.global.file.domain.entity.StoreFileAttachMaster;
import kr.co.apfactory.storesolution.global.file.domain.repository.support.FileSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreFileAttachRepository extends JpaRepository<StoreFileAttach, Long>, FileSupportRepository {
    List<StoreFileAttach> findAllByStoreFileAttachMaster(StoreFileAttachMaster storeFileAttachMaster);

    void deleteAllByStoreFileAttachMaster(StoreFileAttachMaster storeFileAttachMaster);
}
