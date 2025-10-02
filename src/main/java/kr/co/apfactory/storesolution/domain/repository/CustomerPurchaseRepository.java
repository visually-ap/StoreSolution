package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.CustomerPurchase;
import kr.co.apfactory.storesolution.domain.repository.support.CustomerSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.support.StoreSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface CustomerPurchaseRepository extends JpaRepository<CustomerPurchase, Long>, CustomerSupportRepository {

}
