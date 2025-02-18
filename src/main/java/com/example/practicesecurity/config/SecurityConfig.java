package com.example.practicesecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        // 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc", "/joinProc", "/join").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );


        // 로그인 폼 작업
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

        // http
        //        .csrf((auth) -> auth.disable()); // csrf 보호를 비활성화

        http
                .sessionManagement((auth) -> auth

                        // 다중 로그인 설정
                        // 하나의 아이디에서 최대로 허용헐 수 있는 동시 접속 중복 로그인 설정
                        .maximumSessions(1) // 정수만

                        // 위의 값을 초과하면 어떻게 대응할지
                        // true : 초과시 새로운 로그인 차단
                        // false : 초과시 기존 세션 하나 삭제 (큐방식)
                        .maxSessionsPreventsLogin(true));

        http
                // 세션 고정 보호 설정 (세션 고정 공격 막기위해서)
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());

        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));

        return http.build();
    }
}
