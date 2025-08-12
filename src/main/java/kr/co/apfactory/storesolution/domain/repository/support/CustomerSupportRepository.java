package kr.co.apfactory.storesolution.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCounselingDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerSupportRepository {
    List<ResCustomerDTO> selectCustomerList(String searchKeyword, Long storeId);

    ResCustomerDTO selectCustomerDetailByOrderConsultingId(Long orderConsultingId);

    Page<ResCustomerDTO> selectCustomerList(Pageable pageable, SearchDTO searchDTO, Long storeId);

    ResCustomerDTO selectCustomerDetail(Long customerId);

    ResCounselingDTO selectCounselingDetail(Long customerId);
}
