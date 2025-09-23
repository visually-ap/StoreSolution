package kr.co.apfactory.storesolution.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCounselingDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCustomerDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEnvironmentUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResPatternListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CustomerSupportRepository {
    List<ResCustomerDTO> selectCustomerList(String searchKeyword, LocalDate searchDate, Long storeId, ResEnvironmentUpdateDTO resEnvironmentUpdateDTO);

    ResCustomerDTO selectCustomerDetailByReservationId(Long reservationId);

    Page<ResCustomerDTO> selectCustomerList(Pageable pageable, SearchDTO searchDTO, Long storeId);
}
