package com.corelab.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Bean
    public BCryptPasswordEncoder brCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
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
        String profile = environment.getActiveProfiles().length > 0 ? environment.getActiveProfiles()[0] : "local";
        System.out.println("Active Profile : " + profile);
        return profile.equals("local");
    }

    private void setLocalAuthorityConfigure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin().and().csrf().disable();
        this.setOperationAuthorityConfigure(http);
    }

    private void setOperationAuthorityConfigure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/h2-console/**", "/auth/**", "/js/**", "/css/**", "/images/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/account/loginForm")
                .loginProcessingUrl("/account/loginProc")
                .defaultSuccessUrl("/");

    }

}
