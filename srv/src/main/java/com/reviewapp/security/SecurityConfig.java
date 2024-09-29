package com.reviewapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@EnableWebSecurity
@Order(1)
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http.securityMatcher(AntPathRequestMatcher.antMatcher("/**"))
              .csrf(c-> c.disable())
              .authorizeHttpRequests(r-> r.anyRequest().permitAll()).build();
    }
}
