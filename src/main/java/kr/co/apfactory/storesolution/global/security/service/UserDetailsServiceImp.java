package kr.co.apfactory.storesolution.global.security.service;

import kr.co.apfactory.storesolution.domain.repository.UserRepository;
import kr.co.apfactory.storesolution.global.security.dto.LoginInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        // DB에서 아이디를 이용하여 사용 정보 조회
        LoginInfoDTO loginInfo = userRepository.selectLoginInfo(loginId);
        if (loginInfo == null) {
            // 회원이 존재하지 않을 시 예외 처리
            throw new UsernameNotFoundException("아이디, 비밀번호를 확인해 주세요.");
        }

        return loginInfo;
    }
}
