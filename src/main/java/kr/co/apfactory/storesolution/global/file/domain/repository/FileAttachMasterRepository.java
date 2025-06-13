package kr.co.apfactory.storesolution.global.file.domain.repository;

import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttachMaster;
import kr.co.apfactory.storesolution.global.file.domain.repository.support.FileSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileAttachMasterRepository extends JpaRepository<FileAttachMaster, Long>, FileSupportRepository {
}
