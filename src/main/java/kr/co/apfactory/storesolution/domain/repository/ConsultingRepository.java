package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.Consulting;
import kr.co.apfactory.storesolution.domain.repository.support.CustomerSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface ConsultingRepository extends JpaRepository<Consulting, Long>, CustomerSupportRepository {

}
