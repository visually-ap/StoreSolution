package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.CounselingVest;
import kr.co.apfactory.storesolution.domain.repository.support.CounselingSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface CounselingVestRepository extends JpaRepository<CounselingVest, Long>, CounselingSupportRepository {
    CounselingVest findByCounselingCommonId(Long id);
}
