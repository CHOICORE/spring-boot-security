package com.corelab.blog.model;

import com.corelab.blog.entity.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetails implements UserDetails {

    private Account account;

    public PrincipalDetails(Account account) {
        this.account = account;
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override // 계정 잠금 여부
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 비밀번호 만료 여부
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 계정 만료 여부
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정 활성화 여부
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();
        collectors.add(() -> {
            // TODO: 2020-11-03 WebSecurityConfiguration 에서는 hasRole 권한 설정을 ADMIN으로 했는데 반환되는 값은 ROLE_ADMIN로 했는데 왜 인증이 되는지 찾아봐야함
            System.out.println("============================");
            System.out.println(account.getRole());
            System.out.println("============================");
            return account.getRole().toString();
        });
        return collectors;
    }
}
