package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.TempOrderDsPants;
import kr.co.apfactory.storesolution.domain.repository.support.CommonCodeSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface TempOrderDsPantsRepository extends JpaRepository<TempOrderDsPants, Long>, CommonCodeSupportRepository {

}
