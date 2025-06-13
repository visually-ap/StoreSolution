package kr.co.apfactory.storesolution.global.auditor;

import kr.co.apfactory.storesolution.domain.entity.User;
import kr.co.apfactory.storesolution.global.security.dto.LoginInfoDTO;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImp implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || authentication.getPrincipal().equals("anonymousUser")
                || !authentication.isAuthenticated()) {
            return null;
        }

        return Optional.of(((LoginInfoDTO) authentication.getPrincipal()).toEntity());
    }
}
