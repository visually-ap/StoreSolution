package kr.co.apfactory.storesolution.domain.repository.util;

import kr.co.apfactory.storesolution.domain.entity.CounselingCommon;
import kr.co.apfactory.storesolution.domain.repository.support.CustomerSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface CounselingRepository extends JpaRepository<CounselingCommon, Long>, CustomerSupportRepository {
    CounselingCommon findByReservationId(Long reservationId);
}
