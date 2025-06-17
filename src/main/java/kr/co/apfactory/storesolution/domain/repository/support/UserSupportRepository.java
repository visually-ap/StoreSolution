package kr.co.apfactory.storesolution.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEmployeeDetailDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEmployeeListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResMypageDTO;
import kr.co.apfactory.storesolution.global.security.dto.LoginInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserSupportRepository {
    LoginInfoDTO selectLoginInfo(String loginId);

    ResMypageDTO selectUserInfoForMypage(String lang, Long userId);

    Page<ResEmployeeListDTO> selectEmployeeList(String lang, Pageable pageable, SearchDTO searchDTO, Long storeId);

    ResEmployeeDetailDTO selectEmployeeDetail(String lang, Long userId, Long storeId);
}
