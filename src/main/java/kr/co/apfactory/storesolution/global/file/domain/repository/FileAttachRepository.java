package kr.co.apfactory.storesolution.global.file.domain.repository;

import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttach;
import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttachMaster;
import kr.co.apfactory.storesolution.global.file.domain.repository.support.FileSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileAttachRepository extends JpaRepository<FileAttach, Long>, FileSupportRepository {
    List<FileAttach> findAllByFileAttachMaster(FileAttachMaster fileAttachMaster);
}
