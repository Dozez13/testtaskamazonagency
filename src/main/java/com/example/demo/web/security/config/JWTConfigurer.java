package com.example.demo.web.security.config;


import com.example.demo.web.security.auth.ajax.AuthenticationLoginProcessingFilter;
import com.example.demo.web.security.auth.jwt.JWTAuthenticationProcessingFilter;
import com.example.demo.web.security.constant.SecurityConstants;
import com.example.demo.web.security.model.SkipPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class JWTConfigurer extends AbstractHttpConfigurer<JWTConfigurer, HttpSecurity> {


    private AuthenticationFailureHandler failureHandler;

    private AuthenticationSuccessHandler loginSuccessfulHandler;

    private List<AuthenticationProvider> authenticationLoginProviders;

    private ObjectMapper objectMapper;


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        builder.addFilterBefore(buildAuthenticationProcessingFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(buildJWTAuthenticationProcessingFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
        for (AuthenticationProvider provider : authenticationLoginProviders) {
            builder.authenticationProvider(provider);
        }


    }

    private AuthenticationLoginProcessingFilter buildAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationLoginProcessingFilter(SecurityConstants.AUTHENTICATION_URL, authenticationManager, loginSuccessfulHandler, failureHandler, objectMapper);
    }

    private JWTAuthenticationProcessingFilter buildJWTAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        SkipPathRequestMatcher skipPathRequestMatcher = new SkipPathRequestMatcher(Arrays.asList(SecurityConstants.AUTHENTICATION_URL, SecurityConstants.REGISTRATION_URL), SecurityConstants.API_SECURE_URL);
        return new JWTAuthenticationProcessingFilter(skipPathRequestMatcher, authenticationManager, failureHandler);
    }
}
