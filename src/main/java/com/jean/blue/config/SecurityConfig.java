package com.jean.blue.config;

import com.jean.blue.exception.CustomAuthenticationFailureHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring()
                .antMatchers("/fontawesome-free/**")
                .antMatchers("/bootstrap.4.4.1/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/img/**")
                .antMatchers("/lib/**")
                .antMatchers("/sb-admin/**")
                .antMatchers("/vendors/**");
    }

    /**
     *  access(String) : 주어진 SpEL 표현식의 평가 결과가 true이면 접근을 허용
     *  anonymous() : 익명의 사용자의 접근을 허용
     *  authenticated() : 인증된 사용자의 접근을 허용
     *  denyAll() : 무조건 접근을 허용하지 않음
     *  fullyAuthenticated() : 사용자가 완전히 인증되면 접근을 허용(기억되지 않음)
     *  hasAnyAuthority(String...) : 사용자가 주어진 권한 중 어떤 것이라도 있다면 접근을 허용
     *  hasAnyRole(String...) : 사용자가 주어진 역할 중 어떤 것이라도 있다면 접근을 허용
     *  hasAuthority(String) : 사용자가 주어진 권한이 있다면 접근을 허용
     *  hasIpAddress(String) : 주어진 IP로부터 요청이 왔다면 접근을 허용
     *  hasRole(String) : 사용자가 주어진 역할이 있다면 접근을 허용
     *  not() : 다른 접근 방식의 효과를 무효화
     *  permitAll() : 무조건 접근을 허용
     *  rememberMe() : 기억하기를 통해 인증된 사용자의 접근을 허용
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/").permitAll()
                .antMatchers("/fees/**").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/meeting/**").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/user/userInfo").authenticated()
                .antMatchers("/signUp", "/signOut").permitAll()
                .antMatchers("/member/**").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/board/**").authenticated()
                .antMatchers("/**").permitAll()
                .and() // 로그인 설정
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/meeting/meeting")
                .permitAll()
                .failureHandler(customAuthenticationFailureHandler())
                .and() // 로그아웃 설정
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                // 403 예외처리 핸들링
            .exceptionHandling().accessDeniedPage("/member/denied");
        http.sessionManagement().maximumSessions(30).expiredUrl("/login");
//                .and()
//                    .csrf()
//                        .disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler();
    }


}