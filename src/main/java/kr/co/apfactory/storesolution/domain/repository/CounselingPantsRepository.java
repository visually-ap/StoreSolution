package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.CounselingJacket;
import kr.co.apfactory.storesolution.domain.entity.CounselingPants;
import kr.co.apfactory.storesolution.domain.repository.support.CounselingSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface CounselingPantsRepository extends JpaRepository<CounselingPants, Long>, CounselingSupportRepository {
    CounselingPants findByCounselingCommonId(Long id);
}
