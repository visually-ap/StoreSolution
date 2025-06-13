package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.User;
import kr.co.apfactory.storesolution.domain.repository.support.UserSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserSupportRepository {
    boolean existsByLoginId(String loginId);
}
