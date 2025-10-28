package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqConsultingPartnerRegisterDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqRentalItemDTO;
import kr.co.apfactory.storesolution.domain.dto.response.*;
import kr.co.apfactory.storesolution.domain.entity.ConsultingPartner;
import kr.co.apfactory.storesolution.domain.entity.RentalItem;
import kr.co.apfactory.storesolution.domain.entity.Store;
import kr.co.apfactory.storesolution.domain.repository.*;
import kr.co.apfactory.storesolution.global.i18n.I18nUtility;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final PasswordEncoder passwordEncoder;

    private final I18nUtility i18nUtility;

    private final StoreRepository storeRepository;

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final ConsultingPartnerRepository consultingPartnerRepository;
    private final ConsultingPartnerPicRepository consultingPartnerPicRepository;

    private final CustomerPurchaseRepository customerPurchaseRepository;
    private final RentalItemRepository rentalItemRepository;
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public ResStoreInfoDTO getStoreInfo() {
        return storeRepository.selectStoreInfo(LoginUser.getDetails().getId());
    }

    public ResEmployeeDetailDTO getEmployeeDetail(HttpServletRequest request, Long userId) {
        ResEmployeeDetailDTO dto = storeRepository.selectEmployeeDetail(i18nUtility.getLanguage(request), userId, LoginUser.getDetails().getStoreId());
        if (dto == null) {
            throw new IllegalArgumentException();
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ResEmployeeListDTO> getEmployeeList(HttpServletRequest request, Pageable pageable, SearchDTO searchDTO) {
        return storeRepository.selectEmployeeList(i18nUtility.getLanguage(request), pageable, searchDTO, LoginUser.getDetails().getStoreId());
    }

    public List<ResEmployeeListDTO> getEmployeeList() {
        return storeRepository.selectEmployeeList(LoginUser.getDetails().getStoreId());
    }

    @Transactional
    public ResponseDTO getStatisticsList(LocalDate date) {
        ResStatDTO statisticsDto = reservationRepository.selectStatisticsList(LoginUser.getDetails().getStoreId(), date);

        return ResponseDTO.builder()
                .isSuccess(true)
                .result(statisticsDto)
                .build();
    }

    @Transactional(readOnly = true)
    public Page<ResConsultingPartnerListDTO> getConsultingPartnerList(HttpServletRequest request, Pageable pageable, SearchDTO searchDTO) {
        return consultingPartnerRepository.selectConsultingPartnerList(i18nUtility.getLanguage(request), pageable, searchDTO, LoginUser.getDetails().getStoreId());
    }

    @Transactional(readOnly = true)
    public List<ResConsultingPartnerListDTO> getConsultingPartnerList() {
        return consultingPartnerRepository.selectConsultingPartnerList(LoginUser.getDetails().getStoreId());
    }

    public ResConsultingPartnerDetailDTO getConsultingPartnerDetail(Long partnerId) {
        ResConsultingPartnerDetailDTO dto = consultingPartnerRepository.selectConsultingPartnerDetail(partnerId, LoginUser.getDetails().getStoreId());
        if (dto == null) {
            throw new IllegalArgumentException();
        }
        return dto;
    }

    public List<ResConsultingPartnerPicDTO> getConsultingPartnerPicList(Long partnerId) {
        List<ResConsultingPartnerPicDTO> list = consultingPartnerPicRepository.selectConsultingPartnerPicList(partnerId);
        return list;
    }

    @Transactional(readOnly = true)
    public Page<ResRentalItemListDTO> getRentalItemList(Pageable pageable, SearchDTO searchDTO) {
        return rentalItemRepository.selectRentalItemList(pageable, searchDTO, LoginUser.getDetails().getStoreId());
    }

    public ResponseDTO registerRentalItem(ReqRentalItemDTO dto) {
        RentalItem rentalItem = dto.toRentalItemEntity();
        rentalItem.updateStore(Store.builder().id(LoginUser.getDetails().getStoreId()).build());

        rentalItemRepository.save(rentalItem);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("대여복이 등록되었습니다.")
                .build();
    }

    public ResRentalItemDTO getRentalItemDetail(Long rentalItemId) {
        ResRentalItemDTO dto = rentalItemRepository.selectRentalItemDetail(rentalItemId, LoginUser.getDetails().getStoreId());
        if (dto == null) {
            throw new IllegalArgumentException();
        }
        return dto;
    }

    public ResponseDTO updateRentalItem(ReqRentalItemDTO dto) {
        RentalItem rentalItem = rentalItemRepository.findById(dto.getRentalItemId()).orElseThrow(IllegalAccessError::new);
        rentalItem.update(dto);
        rentalItemRepository.save(rentalItem);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("대여복 정보를 수정하였습니다.")
                .build();
    }

    public ResponseDTO deleteRentalItem(Long rentalItemId) {
        RentalItem rentalItem = rentalItemRepository.findById(rentalItemId).orElseThrow(IllegalAccessError::new);
        rentalItem.delete();

        rentalItemRepository.save(rentalItem);

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("대여복 정보를 삭제하였습니다.")
                .build();
    }

    @Transactional(readOnly = true)
    public ResponseDTO getRentalItemList(LocalDate fromDate, LocalDate requestDate, String searchKeyword, Long rentalId) {
        List<ResRentalItemListDTO> list;
        if (rentalId == null) {
            list = rentalItemRepository.selectRentalItemList(fromDate, requestDate, searchKeyword, LoginUser.getDetails().getStoreId());
        } else {
            list = rentalItemRepository.selectRentalItemList(fromDate, requestDate, searchKeyword, LoginUser.getDetails().getStoreId(), rentalId);
        }
        return ResponseDTO.builder()
                .isSuccess(true)
                .result(list)
                .build();
    }

    @Transactional
    public ResponseDTO getEmployeeScheduleList(LocalDate date) {
        List<ResEmployeeScheduleDTO> scheduleList = storeRepository.selectEmployeeScheduleList(LoginUser.getDetails().getStoreId(), date);

        if (scheduleList.size() < 5) {
            int limit = 5 - scheduleList.size();

            for (int i = 0; i < limit; i++) {
                scheduleList.add(
                        ResEmployeeScheduleDTO.builder()
                                .name("")
                                .build()
                );
            }
        }

        return ResponseDTO.builder()
                .isSuccess(true)
                .result(scheduleList)
                .build();
    }
}
