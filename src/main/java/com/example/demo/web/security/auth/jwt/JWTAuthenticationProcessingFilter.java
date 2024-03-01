package com.example.demo.web.security.auth.jwt;


import com.example.demo.core.expection.AuthTokenIsNotPresentRuntimeException;
import com.example.demo.web.security.constant.SecurityConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JWTAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;

    public JWTAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager, AuthenticationFailureHandler failureHandler) {
        super(requiresAuthenticationRequestMatcher, authenticationManager);
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String rawTokenWithPrefix = Optional.ofNullable(request.getHeader(SecurityConstants.AUTHENTICATION_HEADER_NAME)).orElseThrow(() -> new AuthTokenIsNotPresentRuntimeException("Authentication token is not present"));
        String rawTokenWithoutPrefix = rawTokenWithPrefix.substring(SecurityConstants.HEADER_PREFIX.length());
        return this.getAuthenticationManager().authenticate(new JWTAuthenticationToken(rawTokenWithoutPrefix));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authResult);
        SecurityContextHolder.setContext(securityContext);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
