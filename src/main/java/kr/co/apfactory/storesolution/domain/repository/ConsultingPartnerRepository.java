package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.ConsultingPartner;
import kr.co.apfactory.storesolution.domain.entity.Store;
import kr.co.apfactory.storesolution.domain.repository.support.StoreSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface ConsultingPartnerRepository extends JpaRepository<ConsultingPartner, Long>, StoreSupportRepository {
    ConsultingPartner findByIdAndStoreIdAndDeletedIsFalse(Long id, Long storeId);
}
