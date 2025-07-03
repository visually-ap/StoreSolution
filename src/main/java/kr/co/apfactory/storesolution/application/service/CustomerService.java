package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqReservationRegisterDTO;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.repository.*;
import kr.co.apfactory.storesolution.global.i18n.I18nUtility;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final PasswordEncoder passwordEncoder;

    private final I18nUtility i18nUtility;

    private final StoreRepository storeRepository;

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final OrderConsultingRepository orderConsultingRepository;

    private final ReservationRepository reservationRepository;

    private final SiteEnvSettingRepository siteEnvSettingRepository;

    public ResponseDTO registerConsultingReservation(ReqReservationRegisterDTO reqReservationRegisterDTO) {
        Store store = Store.builder().id(LoginUser.getDetails().getStoreId()).build();

        // 고객정보 등록
        Customer customer = reqReservationRegisterDTO.toCustomerEntity();
        customer.updateStore(store);

        customerRepository.save(customer);

        // 주문 상담 정보 등록
        OrderConsulting orderConsulting = reqReservationRegisterDTO.toOrderConsultingEntity();
        orderConsulting.updateCustomer(customer);

        orderConsultingRepository.save(orderConsulting);

        // 예약 정보 등록
        Reservation reservation = reqReservationRegisterDTO.toReservationEntity(1);
        reservation.updateOrderConsulting(orderConsulting);
        reservation.updateReservationManager(User.builder().id(reqReservationRegisterDTO.getReservationManager()).build());
        reservation.updateConsultingManager(User.builder().id(reqReservationRegisterDTO.getConsultingManager()).build());
        if (!reservation.getAllDay()) {
            SiteEnvSetting siteEnvSetting = siteEnvSettingRepository.findByStore(store);
            reservation.updateConsultingDatetime(reqReservationRegisterDTO.getConsultingHour(), reqReservationRegisterDTO.getConsultingMinute(), siteEnvSetting.getTypeTime1());
        }

        reservationRepository.save(reservation);


        return ResponseDTO.builder()
                .isSuccess(true)
                .message("저장하였습니다.")
                .build();
    }
}
