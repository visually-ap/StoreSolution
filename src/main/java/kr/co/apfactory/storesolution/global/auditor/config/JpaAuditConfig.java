package kr.co.apfactory.storesolution.global.auditor.config;

import kr.co.apfactory.storesolution.domain.entity.User;
import kr.co.apfactory.storesolution.global.auditor.AuditorAwareImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {
    @Bean
    public AuditorAware<User> auditorProvider() {
        return new AuditorAwareImp();
    }

}
