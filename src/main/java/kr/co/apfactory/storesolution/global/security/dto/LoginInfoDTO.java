package kr.co.apfactory.storesolution.global.security.dto;

import kr.co.apfactory.storesolution.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginInfoDTO implements UserDetails {
    private Long id;

    private Long storeId;

    private String loginId;

    private String password;

    private String authority;

    private boolean enabled;

    private boolean deleted;

    private String name;

    private String storeName;

    private Long checkAuth;

    private Long adminType;

    @Override // 계정이 가지고 있는 권한을 리턴한다.
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(this.authority));
        return auth;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // 계정 만료 여부

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } // 계정 잠김 여부

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // 계정 비밀번호 만료 여부

    @Override
    public boolean isEnabled() {
        return this.enabled;
    } // 계정 활성화 여부

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .loginId(this.loginId)
                .build();
    }
}
