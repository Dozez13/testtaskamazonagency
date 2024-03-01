package com.example.demo.web.security.auth.ajax;


import com.example.demo.core.expection.AuthMethodIsNotSupportedRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {


    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final ObjectMapper objectMapper;

    public AuthenticationLoginProcessingFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, ObjectMapper objectMapper) {
        super(defaultFilterProcessesUrl, authenticationManager);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.objectMapper = objectMapper;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            throw new AuthMethodIsNotSupportedRuntimeException("Authentication method is not supported");
        }
        UserLoginDto userLoginDto = objectMapper.readValue(request.getReader(), UserLoginDto.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
