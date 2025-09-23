package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqReservationRegisterDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqReservationUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCustomerDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEnvironmentUpdateDTO;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.repository.*;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final SiteEnvSettingRepository siteEnvSettingRepository;
    private final CounselingCommonRepository counselingCommonRepository;
    private final CounselingJacketRepository counselingJacketRepository;
    private final CounselingPantsRepository counselingPantsRepository;
    private final CounselingVestRepository counselingVestRepository;
    private final CounselingCoatRepository counselingCoatRepository;

    public ResponseDTO registerCustomerReservation(ReqReservationRegisterDTO reqReservationRegisterDTO) {
        Store store = Store.builder().id(LoginUser.getDetails().getStoreId()).build();

        if (!reqReservationRegisterDTO.getIsAllday()) {
            SiteEnvSetting siteEnvSetting = siteEnvSettingRepository.findByStore(store);
            reqReservationRegisterDTO.updateConsultingDatetime(siteEnvSetting.getMinuteByType(reqReservationRegisterDTO.getType()));
            // 등록 가능한 일정인지 먼저 확인
            if (reservationRepository.countByConsultingManagerAndConsultingDatetimeFromLessThanAndConsultingDatetimeToGreaterThan(
                    User.builder().id(reqReservationRegisterDTO.getConsultingManager()).build()
                    , reqReservationRegisterDTO.getConsultingDatetimeTo()
                    , reqReservationRegisterDTO.getConsultingDatetimeFrom()) > 0) {
                return ResponseDTO.builder()
                        .message("등록할 수 없는 일정입니다.\n상담담당의 일정을 확인하시기 바랍니다.")
                        .build();
            }
        }

        // 고객정보 등록
        Customer customer = reqReservationRegisterDTO.toCustomerEntity();
        customer.updateStore(store);
        customerRepository.save(customer);

        // 예약 정보 등록
        Reservation reservation = reqReservationRegisterDTO.toReservationEntity(customer);
        reservationRepository.save(reservation);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("저장하였습니다.")
                .build();
    }

    public ResponseDTO getCustomerList(String searchKeyword, LocalDate searchDate, ResEnvironmentUpdateDTO resEnvironmentUpdateDTO) {
        List<ResCustomerDTO> resCustomerList = customerRepository.selectCustomerList(searchKeyword, searchDate, LoginUser.getDetails().getStoreId(), resEnvironmentUpdateDTO);
        return ResponseDTO.builder()
                .isSuccess(true)
                .result(resCustomerList)
                .build();
    }

    public ResponseDTO getCustomerDetailByReservationId(Long reservationId) {
        ResCustomerDTO dto = customerRepository.selectCustomerDetailByReservationId(reservationId);
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

    public ResponseDTO updateConsultingReservation(ReqReservationUpdateDTO reqReservationUpdateDTO) {
        SiteEnvSetting siteEnvSetting = siteEnvSettingRepository.findByStoreId(LoginUser.getDetails().getStoreId());
        reqReservationUpdateDTO.updateConsultingDatetime(siteEnvSetting.getMinuteByType(reqReservationUpdateDTO.getType()));
        // 등록 가능한 일정인지 먼저 확인
        if (!reqReservationUpdateDTO.getIsAllday() && reservationRepository.countByConsultingManagerAndConsultingDatetimeFromLessThanAndConsultingDatetimeToGreaterThan(
                User.builder().id(reqReservationUpdateDTO.getConsultingManager()).build()
                , reqReservationUpdateDTO.getConsultingDatetimeTo()
                , reqReservationUpdateDTO.getConsultingDatetimeFrom()) > 0
        ) {
            return ResponseDTO.builder()
                    .message("등록할 수 없는 일정입니다.\n상담담당의 일정을 확인하시기 바랍니다.")
                    .build();
        }

        Reservation reservation = reservationRepository.findById(reqReservationUpdateDTO.getReservationId()).orElseThrow(IllegalAccessError::new);
        if (reservation == null) {
            return ResponseDTO.builder()
                    .message("상담 예약 내용을 찾을 수 없습니다.")
                    .build();
        }

        // 고객정보 수정
        Customer customer = reservation.getCustomer();
        customer.updateCustomerInfo(reqReservationUpdateDTO);
        customerRepository.save(customer);

        // 예약 정보 등록
        reservation.updateReservation(reqReservationUpdateDTO);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("저장하였습니다.")
                .build();
    }

    public Page<ResCustomerDTO>  getCustomerList(Pageable pageable, SearchDTO searchDTO) {
        Page<ResCustomerDTO> resCustomerList = customerRepository.selectCustomerList(pageable, searchDTO, LoginUser.getDetails().getStoreId());
        return resCustomerList;
    }
}
