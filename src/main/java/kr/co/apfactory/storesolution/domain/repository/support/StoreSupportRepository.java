package kr.co.apfactory.storesolution.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface StoreSupportRepository {
    ResStoreInfoDTO selectStoreInfo(Long userId);

    Page<ResEmployeeListDTO> selectEmployeeList(String lang, Pageable pageable, SearchDTO searchDTO, Long storeId);

    ResEmployeeDetailDTO selectEmployeeDetail(String lang, Long userId, Long storeId);

    List<ResEmployeeListDTO> selectEmployeeList(Long storeId);

    List<ResEmployeeScheduleDTO> selectEmployeeScheduleList(Long storeId, LocalDate date);

    Page<ResConsultingPartnerListDTO> selectConsultingPartnerList(String lang, Pageable pageable, SearchDTO searchDTO, Long storeId);

    ResConsultingPartnerDetailDTO selectConsultingPartnerDetail(Long partnerId, Long storeId);

    List<ResConsultingPartnerListDTO> selectConsultingPartnerList(Long storeId);

    Page<ResRentalItemListDTO> selectRentalItemList(Pageable pageable, SearchDTO searchDTO, Long storeId);

    ResRentalItemDTO selectRentalItemDetail(Long rentalItemId, Long storeId);

    List<ResRentalItemListDTO> selectRentalItemList(LocalDate fromDate, LocalDate requestDate, String searchKeyword, Long storeId);
}
