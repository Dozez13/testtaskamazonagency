package com.example.demo.web.security.auth.ajax;


import com.example.demo.web.security.model.UserAuthenticationInfo;
import com.example.demo.web.security.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthenticationLoginSuccessfulHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserAuthenticationInfo authenticationInfo = (UserAuthenticationInfo) authentication.getPrincipal();
        String jwt = jwtUtil.generateJWTToken(authenticationInfo);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("ROLE", authenticationInfo.getRole());
        tokenMap.put("token", jwt);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        objectMapper.writeValue(response.getWriter(), tokenMap);
        clearAuthenticationAttributes(request);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
