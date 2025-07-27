package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqReservationRegisterDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResStoreInfoDTO;
import kr.co.apfactory.storesolution.domain.repository.StoreRepository;
import kr.co.apfactory.storesolution.domain.repository.UserRepository;
import kr.co.apfactory.storesolution.global.i18n.I18nUtility;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final PasswordEncoder passwordEncoder;

    private final I18nUtility i18nUtility;

    private final StoreRepository storeRepository;

    private final UserRepository userRepository;

    public ResponseDTO registerReservation(ReqReservationRegisterDTO reqReservationRegisterDTO) {
        return ResponseDTO.builder()
                .isSuccess(true)
                .message("저장하였습니다.")
                .build();
    }
}
