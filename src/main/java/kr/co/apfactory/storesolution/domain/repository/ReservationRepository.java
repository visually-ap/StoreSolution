package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.Reservation;
import kr.co.apfactory.storesolution.domain.entity.User;
import kr.co.apfactory.storesolution.domain.repository.support.CustomerSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

// jpa 사용
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, CustomerSupportRepository {
    int countByConsultingManagerAndConsultingDatetimeFromLessThanEqualAndConsultingDatetimeToGreaterThan(User consultingManager, LocalDateTime targetFrom, LocalDateTime targetTo);
    int countByConsultingManagerAndConsultingDatetimeFromLessThanAndConsultingDatetimeToGreaterThan(User consultingManager, LocalDateTime targetFrom, LocalDateTime targetTo);
}
