package com.example.demo.web.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.web.security.constant.SecurityConstants;
import com.example.demo.web.security.model.UserAuthenticationInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JWTUtil {


    public String generateJWTToken(UserAuthenticationInfo authenticationInfo) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        return JWT.create()
                .withSubject(authenticationInfo.getEmail())
                .withClaim(SecurityConstants.ROLE_CLAIM, authenticationInfo.getRole())
                .withClaim(SecurityConstants.SCOPES_CLAIM, authenticationInfo.getGrantedAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuer(SecurityConstants.ISSUER)
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plus(SecurityConstants.EXPIRATION_TOKEN_DATE_IN_MIN, ChronoUnit.MINUTES)))
                .sign(algorithm);
    }

    public DecodedJWT decodeRawJWTToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        return JWT.require(algorithm)
                .withIssuer(SecurityConstants.ISSUER)
                .build()
                .verify(token);//Reusable verifier instance

    }
}
