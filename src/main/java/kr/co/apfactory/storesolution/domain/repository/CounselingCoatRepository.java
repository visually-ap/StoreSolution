package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.CounselingCoat;
import kr.co.apfactory.storesolution.domain.entity.CounselingJacket;
import kr.co.apfactory.storesolution.domain.repository.support.CounselingSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface CounselingCoatRepository extends JpaRepository<CounselingCoat, Long>, CounselingSupportRepository {
    CounselingCoat findByCounselingCommonId(Long id);
}
