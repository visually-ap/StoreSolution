package kr.co.apfactory.storesolution.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqCustomerPurchaseDTO;
import kr.co.apfactory.storesolution.domain.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CustomerSupportRepository {
    List<ResCustomerDTO> selectCustomerList(String searchKeyword, LocalDate searchDate, Long storeId, ResEnvironmentUpdateDTO resEnvironmentUpdateDTO);

    ResCustomerDTO selectCustomerDetailByReservationId(Long reservationId);

    Page<ResCustomerDTO> selectCustomerList(Pageable pageable, SearchDTO searchDTO, Long storeId);

    List<ResCustomerPurchaseListDTO> selectCustomerPurchaseList(Long customerId, Long storeId);

    ResCustomerPurchaseDTO selectCustomerPurchaseDetail(Long purchaseId);

    List<ResCustomerPaymentListDTO> selectCustomerPaymentList(Long customerId, Long storeId);

    ResCustomerPaymentDTO selectCustomerPaymentDetail(Long paymentId);

    List<ResRentalListDTO> selectCustomerRentalList(Long customerId);

    ResRentalDTO selectCustomerRentalDetail(Long rentalId);

    List<ResCustomerReservationDTO> selectCustomerReservationList(Long customerId, ResEnvironmentUpdateDTO resEnvironmentUpdateDTO);
}
