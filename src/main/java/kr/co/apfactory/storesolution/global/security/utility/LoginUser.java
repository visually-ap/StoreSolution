package kr.co.apfactory.storesolution.global.security.utility;

import kr.co.apfactory.storesolution.global.security.dto.LoginInfoDTO;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginUser {
    // 로그인된 사용자 정보 로드
    public static LoginInfoDTO getDetails() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null
                || SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return null;
        } else {
            return (LoginInfoDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
    }
}
