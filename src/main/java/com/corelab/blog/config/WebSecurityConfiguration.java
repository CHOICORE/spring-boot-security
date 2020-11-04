package com.corelab.blog.config;

import com.corelab.blog.model.ProfilesType;
import com.corelab.blog.service.PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터 추가
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증 선행 체크
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @Value(value = "${spring.profiles.active ?:local}")
    private String profiles;

    @Bean
    public BCryptPasswordEncoder brCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailsService).passwordEncoder(brCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (isLocalEnvironment()) {
            /*
             로컬환경에서 개발할 때 csrf disabled
             h2-console - frameOptions disabled() or sameOrigin()
             */
            setLocalAuthorityConfigure(http);
        } else {
            // 운영 시 필요한 전략
            setOperationAuthorityConfigure(http);
        }
    }


    private boolean isLocalEnvironment() {
        // // TODO: 프로파일을 구분하는 함수 - ENUM TYPE으로 변경
        System.out.println(ProfilesType.LOCAL);
        System.out.println(ProfilesType.LOCAL.name());
        System.out.println(ProfilesType.LOCAL.toString());
        System.out.println("=============================================================");
        System.out.println(profiles);
        System.out.println("=============================================================");
//        String profile = environment.getActiveProfiles().length > 0 ? environment.getActiveProfiles()[0] : "local";
        return profiles.equals("local") || profiles.equals("dev");
    }

    private void setLocalAuthorityConfigure(HttpSecurity http) throws Exception {
        http.headers()
                .frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console").permitAll();

        this.setOperationAuthorityConfigure(http);
    }

    private void setOperationAuthorityConfigure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/user/**").authenticated()
                // PrincipalDetailsService 에서 Authority 를 가져와서 확인할 때 자동으로 ROLE 이라는 접두어를 붙어서 확인한다.
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/account/login")
                .loginProcessingUrl("/account/login")
                .defaultSuccessUrl("/");

    }

}
