package kr.co.apfactory.storesolution.global.security.config;

import kr.co.apfactory.storesolution.global.security.entrypoint.AjaxAwareAuthenticationEntryPoint;
import kr.co.apfactory.storesolution.global.security.handler.LoginFailureHandler;
import kr.co.apfactory.storesolution.global.security.handler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

// 해당 클래스를 Configuration으로 등록합니다
@Configuration

// Spring Security를 활성화 시킵니다.
@EnableWebSecurity

// Controller에서 특정 페이지에 특정 권한이 있는 유저만 접근을 허용할 경우
// @PreAuthorize 어노테이션을 사용하는데, 해당 어노테이션에 대한 설정을
// 활성화시키는 어노테이션입니다. (필수는 아닙니다.)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // HttpSecurity를 통해 HTTP 요청에 대한 보안을 설정할 수 있습니다.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http 요청에 대해서 모든 사용자가 /** 경로로 요청할 수 있지만
        // , /member/** , /admin/** 경로는 인증된 사용자만 요청이 가능합니다.

        /*
        authorizeRequests() - HttpServletRequest 요청 URL에 따라 접근 권한을 설정합니다.
        antMatchers("pathPattern") - 요청 URL 경로 패턴을 지정합니다.
        authenticated() - 인증된 유저만 접근을 허용합니다.
        permitAll() - 모든 유저에게 접근을 허용합니다.
        anonymous() - 인증되지 않은 유저만 허용합니다.
        denyAll() - 모든 유저에 대해 접근을 허용하지 않습니다.
         */
        http
                .csrf()
                .ignoringAntMatchers(
                        "/counseling/fabric/save"
                        , "/counseling/design/save"
                        , "/counseling/size/save"
                )
                .and()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/dist/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/plugins/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/**").hasAnyRole("USER", "EMPLOYEE");

        http.formLogin()    // form Login 설정을 진행합니다.
                .loginPage("/login")    // 커스텀 로그인 페이지 경로와 로그인 인증 경로를 등록합니다.
                .usernameParameter("loginId") // HTML form 태그에서 받는 name = "id"
                .passwordParameter("password")
                .failureForwardUrl("/login?error") // 로그인 실패시 이동하는 경로
                .successHandler(successHandler()) // 로그인 성공시 처리
                .failureHandler(failureHandler()) // 로그인 실패시 처리
                .permitAll();

        http.logout()       // 로그아웃 설정을 진행합니다.
                .logoutUrl("/logout")          // 로그아웃 처리 URL
                .logoutSuccessUrl("/login")    // 로그아웃 성공 시 이동할 경로를 지정합니다.
                .invalidateHttpSession(false)  // 로그아웃 성공 시 세션을 제거합니다.
                .deleteCookies("JSESSIONID");

        http.exceptionHandling()
                // 권한이 없는 사용자가 접근했을 경우 이동할 경로를 지정합니다.
                .accessDeniedPage("/deny");

        // 인증되지 않은 요청중 AJAX요청일 경우 403으로 응답, AJAX요청이 아닐 경우 login으로 리다이렉트
        http.exceptionHandling()
                .authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/login"));

        //csrf예외처리
        //http.csrf().ignoringAntMatchers("/ckeditor/**");
    }

    // 비밀번호를 복호화/암호화하는 로직이 담긴 객체를 Bean으로 등록합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 로그인이 정상적으로 처리 되었을 때 동작하는 객체를 Bean으로 등록
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new LoginSuccessHandler();
    }

    // 로그인이 실패하였을때 동작하는 객체를 Bean으로 등록
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new LoginFailureHandler();
    }

    // 로그아웃시 세션이 종료 되면 동작하는 객체를 Bean으로 등록
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}

