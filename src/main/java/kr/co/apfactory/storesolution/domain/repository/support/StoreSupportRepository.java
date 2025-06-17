package kr.co.apfactory.storesolution.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.response.ResStoreInfoDTO;

public interface StoreSupportRepository {
    ResStoreInfoDTO selectStoreInfo(Long userId);
}
