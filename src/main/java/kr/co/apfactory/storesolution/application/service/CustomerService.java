package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqReservationRegisterDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqReservationUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCounselingDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCustomerDTO;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.repository.*;
import kr.co.apfactory.storesolution.global.i18n.I18nUtility;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        // 등록가능한 일정인지부터 확인해보자
        LocalDateTime consultingDatetime = reqReservationRegisterDTO.getConsultingDate().atTime(Integer.parseInt(reqReservationRegisterDTO.getConsultingHour()), Integer.parseInt(reqReservationRegisterDTO.getConsultingMinute()));
        if (reservationRepository.countByConsultingManagerAndConsultingDatetimeFromLessThanEqualAndConsultingDatetimeToGreaterThan(User.builder().id(reqReservationRegisterDTO.getConsultingManager()).build(), consultingDatetime, consultingDatetime) > 0) {
            return ResponseDTO.builder()
                    .message("등록할 수 없는 일정입니다.\n상담담당의 일정을 확인하시기 바랍니다.")
                    .build();
        }

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

    public ResponseDTO getCustomerList(String searchKeyword) {
        List<ResCustomerDTO> resCustomerList = customerRepository.selectCustomerList(searchKeyword, LoginUser.getDetails().getStoreId());
        return ResponseDTO.builder()
                .isSuccess(true)
                .result(resCustomerList)
                .build();
    }

    public ResponseDTO getCustomerDetailByOrderConsultingId(Long orderConsultingId) {
        ResCustomerDTO dto = customerRepository.selectCustomerDetailByOrderConsultingId(orderConsultingId);
        if (dto == null) {
            return ResponseDTO.builder()
                    .message("고객 정보를 찾을 수 없습니다.")
                    .build();
        }

        dto.setConsultingDatetime();

        return ResponseDTO.builder()
                .isSuccess(true)
                .result(dto)
                .build();
    }

    public ResponseDTO updateConsultingReservation(ReqReservationUpdateDTO  reqReservationUpdateDTO) {
        OrderConsulting orderConsulting = orderConsultingRepository.findById(reqReservationUpdateDTO.getOrderConsultingId()).orElse(null);
        if (orderConsulting == null) {
            return ResponseDTO.builder()
                    .message("상담 예약 내용을 찾을 수 없습니다.")
                    .build();
        }

        Reservation reservation = reservationRepository.findByOrderConsulting(orderConsulting);
        LocalDateTime consultingDatetimeFrom = reservation.getConsultingDatetimeFrom();
        LocalDateTime consultingDatetimeTo = reservation.getConsultingDatetimeTo();

        // 이전 상담 일정 내용 저장 후 지우고
        reservation.updateConsultingDatetimeFrom(null);
        reservation.updateConsultingDatetimeTo(null);

        // 등록가능한 일정인지 확인해보자
        LocalDateTime consultingDatetime = reqReservationUpdateDTO.getConsultingDate().atTime(Integer.parseInt(reqReservationUpdateDTO.getConsultingHour()), Integer.parseInt(reqReservationUpdateDTO.getConsultingMinute()));
        if (reservationRepository.countByConsultingManagerAndConsultingDatetimeFromLessThanEqualAndConsultingDatetimeToGreaterThan(User.builder().id(reqReservationUpdateDTO.getConsultingManager()).build(), consultingDatetime, consultingDatetime) > 0) {
            // 등록할 수 없는 일정이라면 원래대로 원복한다.
            reservation.updateConsultingDatetimeFrom(consultingDatetimeFrom);
            reservation.updateConsultingDatetimeTo(consultingDatetimeTo);

            reservationRepository.save(reservation);

            return ResponseDTO.builder()
                    .message("등록할 수 없는 일정입니다.\n상담담당의 일정을 확인하시기 바랍니다.")
                    .build();
        }

        Store store = Store.builder().id(LoginUser.getDetails().getStoreId()).build();

        // 고객정보 수정
        Customer customer = orderConsulting.getCustomer();
        customer.updateCustomerInfo(reqReservationUpdateDTO);
        customerRepository.save(customer);

        // 주문 상담 정보 등록 - 추후 금액 작성 시
//        orderConsultingRepository.save(orderConsulting);

        // 예약 정보 등록
        reservation.updateOrderConsulting(orderConsulting);
        reservation.updateReservationManager(User.builder().id(reqReservationUpdateDTO.getReservationManager()).build());
        reservation.updateConsultingManager(User.builder().id(reqReservationUpdateDTO.getConsultingManager()).build());
        if (!reservation.getAllDay()) {
            SiteEnvSetting siteEnvSetting = siteEnvSettingRepository.findByStore(store);
            reservation.updateConsultingDatetime(reqReservationUpdateDTO.getConsultingHour(), reqReservationUpdateDTO.getConsultingMinute(), siteEnvSetting.getTypeTime1());
        }

        reservationRepository.save(reservation);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("저장하였습니다.")
                .build();
    }

    public Page<ResCustomerDTO>  getCustomerList(Pageable pageable, SearchDTO searchDTO) {
        Page<ResCustomerDTO> resCustomerList = customerRepository.selectCustomerList(pageable, searchDTO, LoginUser.getDetails().getStoreId());
        return resCustomerList;
    }

    public ResCustomerDTO getCustomerDetail(Long id) {
        ResCustomerDTO dto = customerRepository.selectCustomerDetail(id);
        return dto;
    }

    public ResCounselingDTO getCounselingDetail(Long customerId) {
        ResCounselingDTO dto = customerRepository.selectCounselingDetail(customerId);
        if (dto == null) {
            dto = new ResCounselingDTO();
        }
        return dto;
    }
}
