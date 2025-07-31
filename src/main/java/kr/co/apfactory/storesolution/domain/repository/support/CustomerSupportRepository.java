package kr.co.apfactory.storesolution.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.response.ResCustomerDTO;

import java.util.List;

public interface CustomerSupportRepository {
    List<ResCustomerDTO> selectCustomerList(String searchKeyword, Long storeId);
}
