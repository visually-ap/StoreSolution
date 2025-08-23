package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.CounselingCommon;
import kr.co.apfactory.storesolution.domain.repository.support.CounselingSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.support.CustomerSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface CounselingCommonRepository extends JpaRepository<CounselingCommon, Long>, CounselingSupportRepository {
    CounselingCommon findByStoreIdAndReservationId(Long storeId, Long reservationId);
}
