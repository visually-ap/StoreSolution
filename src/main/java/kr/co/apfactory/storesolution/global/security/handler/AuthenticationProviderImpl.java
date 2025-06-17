package kr.co.apfactory.storesolution.global.security.handler;

import kr.co.apfactory.storesolution.global.security.dto.LoginInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final UserDetailsService svc;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = (String) authentication.getCredentials();

        LoginInfoDTO loginInfoDTO = (LoginInfoDTO) svc.loadUserByUsername(id);

        if (!loginInfoDTO.isEnabled()) {
            throw new DisabledException(id);
        }

        if (!passwordEncoder.matches(password, loginInfoDTO.getPassword())) {
            throw new BadCredentialsException("아이디 또는 비밀번호를 확인해 주세요.");
        }
        return new UsernamePasswordAuthenticationToken(loginInfoDTO, password, loginInfoDTO.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
