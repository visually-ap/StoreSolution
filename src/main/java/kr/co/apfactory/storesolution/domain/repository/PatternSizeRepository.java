package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.PatternSize;
import kr.co.apfactory.storesolution.domain.repository.support.CommonCodeSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface PatternSizeRepository extends JpaRepository<PatternSize, Long>, CommonCodeSupportRepository {
}
