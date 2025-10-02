package kr.co.apfactory.storesolution.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.response.ResMypageDTO;
import kr.co.apfactory.storesolution.global.security.dto.LoginInfoDTO;

public interface UserSupportRepository {
    LoginInfoDTO selectLoginInfo(String loginId);

    ResMypageDTO selectUserInfoForMypage(String lang, Long userId);
}
