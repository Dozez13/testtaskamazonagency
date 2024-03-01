package com.example.demo.web.security.config;


import com.example.demo.web.security.constant.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    @Autowired
    private AuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private AuthenticationFailureHandler authenticationLoginFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationLoginSuccessfulHandler;
    @Autowired
    private AuthenticationProvider loginProvider;
    @Autowired
    private AuthenticationProvider jwtProvider;

    @Autowired
    private ObjectMapper objectMapper;


    @Bean
    public SecurityFilterChain appHttpSecurity(HttpSecurity appHttpSecurity) throws Exception {
        appHttpSecurity
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.API_SECURE_URL).authenticated()
                .and().apply(buildJWTConfigurer());
        return appHttpSecurity.build();
    }


    private JWTConfigurer buildJWTConfigurer() {
        List<AuthenticationProvider> authenticationProviders = Arrays.asList(loginProvider, jwtProvider);
        return new JWTConfigurer(authenticationLoginFailureHandler, authenticationLoginSuccessfulHandler, authenticationProviders, objectMapper);
    }

}
