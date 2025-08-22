package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEmployeeListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResStoreInfoDTO;
import kr.co.apfactory.storesolution.domain.repository.StoreRepository;
import kr.co.apfactory.storesolution.domain.repository.UserRepository;
import kr.co.apfactory.storesolution.global.i18n.I18nUtility;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final PasswordEncoder passwordEncoder;

    private final I18nUtility i18nUtility;

    private final StoreRepository storeRepository;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ResStoreInfoDTO getStoreInfo() {
        return storeRepository.selectStoreInfo(LoginUser.getDetails().getId());
    }

    @Transactional(readOnly = true)
    public Page<ResEmployeeListDTO> getEmployeeList(HttpServletRequest request, Pageable pageable, SearchDTO searchDTO) {
        return userRepository.selectEmployeeList(i18nUtility.getLanguage(request), pageable, searchDTO, LoginUser.getDetails().getStoreId());
    }
}
