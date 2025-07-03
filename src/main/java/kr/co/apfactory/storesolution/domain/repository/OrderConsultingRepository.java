package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.OrderConsulting;
import kr.co.apfactory.storesolution.domain.repository.support.CustomerSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface OrderConsultingRepository extends JpaRepository<OrderConsulting, Long>, CustomerSupportRepository {

}
