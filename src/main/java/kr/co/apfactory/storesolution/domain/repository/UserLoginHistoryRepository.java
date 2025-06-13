package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.UserLoginHistory;
import kr.co.apfactory.storesolution.domain.repository.support.UserSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface UserLoginHistoryRepository extends JpaRepository<UserLoginHistory, Long>, UserSupportRepository {

}
