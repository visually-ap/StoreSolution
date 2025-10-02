package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.request.*;
import kr.co.apfactory.storesolution.domain.dto.response.*;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.repository.*;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private final CustomerPurchaseRepository customerPurchaseRepository;
    private final ConsultingPartnerRepository consultingPartnerRepository;
    private final CustomerPaymentRepository customerPaymentRepository;
    private final RentalRepository rentalRepository;
    private final RentalItemRepository rentalItemRepository;

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
        if (!reqReservationUpdateDTO.getIsAllday() && reservationRepository.countByConsultingManagerAndConsultingDatetimeFromLessThanAndConsultingDatetimeToGreaterThanAndIdNot(
                User.builder().id(reqReservationUpdateDTO.getConsultingManager()).build()
                , reqReservationUpdateDTO.getConsultingDatetimeTo()
                , reqReservationUpdateDTO.getConsultingDatetimeFrom()
                , reqReservationUpdateDTO.getReservationId()) > 0
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
        reservationRepository.save(reservation);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("저장하였습니다.")
                .build();
    }

    public ResponseDTO savePartnerAndGetConsultingPartnerDetailById(Long customerId, Long partnerId) {
        ResConsultingPartnerDetailDTO dto = new ResConsultingPartnerDetailDTO();
        if (partnerId != null) {
            dto = consultingPartnerRepository.selectConsultingPartnerDetail(partnerId, LoginUser.getDetails().getStoreId());
            if (dto == null) {
                return ResponseDTO.builder()
                        .message("잘못된 접근입니다.")
                        .build();
            }
        }

        Customer customer = customerRepository.findById(customerId).orElseThrow(IllegalAccessError::new);
        customer.updateConsultingPartner(partnerId == null ? null : ConsultingPartner.builder().id(partnerId).build());
        customerRepository.save(customer);

        return ResponseDTO.builder()
                .isSuccess(true)
                .result(dto)
                .build();
    }

    public Page<ResCustomerDTO>  getCustomerList(Pageable pageable, SearchDTO searchDTO) {
        Page<ResCustomerDTO> resCustomerList = customerRepository.selectCustomerList(pageable, searchDTO, LoginUser.getDetails().getStoreId());
        return resCustomerList;
    }

    public ResCustomerDTO getCustomerDetailById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(IllegalAccessError::new);

        return ResCustomerDTO.builder()
                .customerId(customer.getId())
                .name1(customer.getName1())
                .mobile1(customer.getMobile1())
                .name2(customer.getName2())
                .mobile2(customer.getMobile2())
                .photoDate(customer.getPhotoDate())
                .photoPlace(customer.getPhotoPlace())
                .weddingDate(customer.getWeddingDate())
                .weddingPlace(customer.getWeddingPlace())
                .consultingPartnerId(customer.getConsultingPartner() == null ? null : customer.getConsultingPartner().getId())
                .build();
    }

    public List<ResCustomerPurchaseListDTO> getCustomerPurchaseList(Long customerId) {
        return customerPurchaseRepository.selectCustomerPurchaseList(customerId, LoginUser.getDetails().getStoreId());
    }

    public ResponseDTO registerCustomerPurchase(ReqCustomerPurchaseDTO reqCustomerPurchaseDTO) {
        Customer customer = customerRepository.findByIdAndStoreId(reqCustomerPurchaseDTO.getCustomerId(), LoginUser.getDetails().getStoreId());
        if (customer == null) {
            return ResponseDTO.builder()
                    .message("고객 정보를 찾을 수 없습니다.")
                    .build();
        }

        customerPurchaseRepository.save(reqCustomerPurchaseDTO.toCustomerPurchaseEntity(customer));

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("구매내역을 등록하였습니다.")
                .build();
    }

    public ResponseDTO getCustomerPurchaseDetailById(Long purchaseId) {
        ResCustomerPurchaseDTO resCustomerPurchaseDTO = customerPurchaseRepository.selectCustomerPurchaseDetail(purchaseId);
        if (resCustomerPurchaseDTO == null) {
            return ResponseDTO.builder()
                    .message("잘못된 접근입니다.")
                    .build();
        }

        return ResponseDTO.builder()
                .isSuccess(true)
                .result(resCustomerPurchaseDTO)
                .build();
    }

    public ResponseDTO updateCustomerPurchase(ReqCustomerPurchaseDTO reqCustomerPurchaseDTO) {
        CustomerPurchase customerPurchase = customerPurchaseRepository.findById(reqCustomerPurchaseDTO.getPurchaseId()).orElseThrow(IllegalAccessError::new);
        customerPurchase.update(reqCustomerPurchaseDTO);

        customerPurchaseRepository.save(customerPurchase);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("구매내역을 수정하였습니다.")
                .build();
    }

    public ResponseDTO deleteCustomerPurchase(Long purchaseId) {
        CustomerPurchase customerPurchase = customerPurchaseRepository.findById(purchaseId).orElseThrow(IllegalAccessError::new);
        customerPurchase.delete();

        customerPurchaseRepository.save(customerPurchase);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("구매내역을 삭제하였습니다.")
                .build();
    }

    public List<ResCustomerPaymentListDTO> getCustomerPaymentList(Long customerId) {
        return customerPurchaseRepository.selectCustomerPaymentList(customerId, LoginUser.getDetails().getStoreId());
    }

    public ResponseDTO registerCustomerPayment(ReqCustomerPaymentDTO reqCustomerPaymentDTO) {
        Customer customer = customerRepository.findByIdAndStoreId(reqCustomerPaymentDTO.getCustomerId(), LoginUser.getDetails().getStoreId());
        if (customer == null) {
            return ResponseDTO.builder()
                    .message("고객 정보를 찾을 수 없습니다.")
                    .build();
        }

        customerPaymentRepository.save(reqCustomerPaymentDTO.toCustomerPaymentEntity(customer));

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("구매내역을 등록하였습니다.")
                .build();
    }

    public ResponseDTO getCustomerPaymentDetailById(Long paymentId) {
        ResCustomerPaymentDTO resCustomerPaymentDTO = customerPurchaseRepository.selectCustomerPaymentDetail(paymentId);
        if (resCustomerPaymentDTO == null) {
            return ResponseDTO.builder()
                    .message("잘못된 접근입니다.")
                    .build();
        }

        return ResponseDTO.builder()
                .isSuccess(true)
                .result(resCustomerPaymentDTO)
                .build();
    }

    public ResponseDTO updateCustomerPayment(ReqCustomerPaymentDTO reqCustomerPaymentDTO) {
        CustomerPayment customerPayment = customerPaymentRepository.findById(reqCustomerPaymentDTO.getPaymentId()).orElseThrow(IllegalAccessError::new);
        customerPayment.update(reqCustomerPaymentDTO);

        customerPaymentRepository.save(customerPayment);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("결제내역을 수정하였습니다.")
                .build();
    }

    public ResponseDTO deleteCustomerPayment(Long paymentId) {
        CustomerPayment customerPayment = customerPaymentRepository.findById(paymentId).orElseThrow(IllegalAccessError::new);
        customerPayment.delete();

        customerPaymentRepository.save(customerPayment);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("결제내역을 삭제하였습니다.")
                .build();
    }

    public List<ResCounselingDTO> getCounselingList(Long customerId) {
        List<ResCounselingDTO> list = counselingCommonRepository.selectCounselingList(customerId, LoginUser.getDetails().getStoreId());

        for (ResCounselingDTO dto : list) {
            dto.setFabricInfo();
        }
        return list;
    }

    public List<ResRentalListDTO> getCustomerRentalList(Long customerId) {
        List<ResRentalListDTO> list = rentalRepository.selectCustomerRentalList(customerId);

        for (ResRentalListDTO dto : list) {
            dto.setRentingState();
        }
        return list;
    }

    public ResponseDTO registerCustomerRental(ReqRentalDTO reqRentalDTO) {
        Rental rental = reqRentalDTO.toRentalEntity();
        rentalRepository.save(rental);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("대여 정보를 등록하였습니다.")
                .build();
    }

    public ResponseDTO getCustomerRentalDetailById(Long rentalId) {
        ResRentalDTO resRentalDTO = rentalRepository.selectCustomerRentalDetail(rentalId);
        if (resRentalDTO == null) {
            return ResponseDTO.builder()
                    .message("잘못된 접근입니다.")
                    .build();
        }

        resRentalDTO.setRentingState();

        return ResponseDTO.builder()
                .isSuccess(true)
                .result(resRentalDTO)
                .build();
    }

    public ResponseDTO updateCustomerRental(ReqRentalDTO reqRentalDTO) {
        Rental rental = rentalRepository.findById(reqRentalDTO.getRentalId()).orElseThrow(IllegalAccessError::new);
        rental.update(reqRentalDTO);
        rentalRepository.save(rental);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("수정하였습니다.")
                .build();
    }

    public ResponseDTO completeCustomerRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(IllegalAccessError::new);
        rental.updateRenting(true);
        rentalRepository.save(rental);

        // 대여복 렌탈 처리
        RentalItem rentalItem = rental.getRentalItem();
        rentalItem.updateRenting(true);
        rentalItemRepository.save(rentalItem);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("대여 완료 처리하였습니다.")
                .build();
    }

    public ResponseDTO cancelCompleteCustomerRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(IllegalAccessError::new);
        rental.updateRenting(false);
        rentalRepository.save(rental);

        // 대여복 렌탈 취소 처리
        RentalItem rentalItem = rental.getRentalItem();
        rentalItem.updateRenting(false);
        rentalItemRepository.save(rentalItem);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("대여 취소 처리하였습니다.")
                .build();
    }

    public ResponseDTO backCustomerRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(IllegalAccessError::new);
        rental.back();
        rentalRepository.save(rental);

        // 대여복 반납 처리
        RentalItem rentalItem = rental.getRentalItem();
        rentalItem.updateRenting(false);
        rentalItemRepository.save(rentalItem);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("반납 완료 처리하였습니다.")
                .build();
    }

    public ResponseDTO cancelBackCustomerRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(IllegalAccessError::new);
        rental.cancelBack();
        rentalRepository.save(rental);

        // 대여복 반납 취소 처리
        RentalItem rentalItem = rental.getRentalItem();
        rentalItem.updateRenting(true);
        rentalItemRepository.save(rentalItem);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("반납 취소 처리하였습니다.")
                .build();
    }

    public ResponseDTO updateCustomerRentalDate(Long rentalId, LocalDate toDate) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(IllegalAccessError::new);
        rental.updateToDate(toDate);
        rentalRepository.save(rental);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("반납 취소 처리하였습니다.")
                .build();
    }

    public ResponseDTO deleteCustomerRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(IllegalAccessError::new);
        rental.delete();
        rentalRepository.save(rental);

        // 대여복 상태 변경 처리
        RentalItem rentalItem = rental.getRentalItem();
        rentalItem.updateRenting(false);
        rentalItemRepository.save(rentalItem);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("삭제하였습니다.")
                .build();
    }

    public List<ResCustomerReservationDTO> getCustomerReservationList(Long customerId, ResEnvironmentUpdateDTO resEnvironmentUpdateDTO) {
        List<ResCustomerReservationDTO> list = reservationRepository.selectCustomerReservationList(customerId, resEnvironmentUpdateDTO);

        return list;
    }

    public ResponseDTO registerCustomerReservation(ReqCustomerReservationDTO reqCustomerReservationDTO) {
        if (!reqCustomerReservationDTO.getIsAllday()) {
            SiteEnvSetting siteEnvSetting = siteEnvSettingRepository.findByStoreId(LoginUser.getDetails().getStoreId());
            reqCustomerReservationDTO.updateConsultingDatetime(siteEnvSetting.getMinuteByType(reqCustomerReservationDTO.getReservationType()));
            // 등록 가능한 일정인지 먼저 확인
            if (reservationRepository.countByConsultingManagerAndConsultingDatetimeFromLessThanAndConsultingDatetimeToGreaterThan(
                    User.builder().id(reqCustomerReservationDTO.getConsultingManager()).build()
                    , reqCustomerReservationDTO.getConsultingDatetimeTo()
                    , reqCustomerReservationDTO.getConsultingDatetimeFrom()) > 0) {
                return ResponseDTO.builder()
                        .message("등록할 수 없는 일정입니다.\n상담담당의 일정을 확인하시기 바랍니다.")
                        .build();
            }
        }

        // 예약 정보 등록
        Reservation reservation = reqCustomerReservationDTO.toReservationEntity();
        reservationRepository.save(reservation);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("등록하였습니다.")
                .build();
    }

    public ResponseDTO getCustomerReservationDetail(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(IllegalAccessError::new);

        ResCustomerReservationDTO dto = ResCustomerReservationDTO.builder()
                .reservationId(reservation.getId())
                .allDay(reservation.getAllDay())
                .type(reservation.getType())
                .consultingManagerId(reservation.getConsultingManager().getId())
                .consultingDate(reservation.getConsultingDate())
                .consultingDatetimeFrom(reservation.getConsultingDatetimeFrom())
                .consultingDatetimeTo(reservation.getConsultingDatetimeTo())
                .completed(reservation.getCompleted())
                .contract(reservation.getContract())
                .memo(reservation.getMemo())
                .build();

        dto.setConsultingDatetime();

        return ResponseDTO.builder()
                .isSuccess(true)
                .result(dto)
                .build();
    }

    public ResponseDTO updateCustomerReservation(ReqCustomerReservationDTO reqCustomerReservationDTO) {
        SiteEnvSetting siteEnvSetting = siteEnvSettingRepository.findByStoreId(LoginUser.getDetails().getStoreId());
        reqCustomerReservationDTO.updateConsultingDatetime(siteEnvSetting.getMinuteByType(reqCustomerReservationDTO.getReservationType()));
        // 등록 가능한 일정인지 먼저 확인
        if (!reqCustomerReservationDTO.getIsAllday() && reservationRepository.countByConsultingManagerAndConsultingDatetimeFromLessThanAndConsultingDatetimeToGreaterThanAndIdNot(
                User.builder().id(reqCustomerReservationDTO.getConsultingManager()).build()
                , reqCustomerReservationDTO.getConsultingDatetimeTo()
                , reqCustomerReservationDTO.getConsultingDatetimeFrom()
                , reqCustomerReservationDTO.getReservationId()) > 0
        ) {
            return ResponseDTO.builder()
                    .message("등록할 수 없는 일정입니다.\n상담담당의 일정을 확인하시기 바랍니다.")
                    .build();
        }

        Reservation reservation = reservationRepository.findById(reqCustomerReservationDTO.getReservationId()).orElseThrow(IllegalAccessError::new);
        if (reservation == null) {
            return ResponseDTO.builder()
                    .message("상담 예약 내용을 찾을 수 없습니다.")
                    .build();
        }

        // 예약 정보 등록
        reservation.updateReservation(reqCustomerReservationDTO);
        reservationRepository.save(reservation);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("저장하였습니다.")
                .build();
    }

    public ResponseDTO completeCustomerReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(IllegalAccessError::new);
        reservation.completeCounseling(User.builder().id(LoginUser.getDetails().getId()).build());

        reservationRepository.save(reservation);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("완료 처리하였습니다.")
                .build();
    }
}
