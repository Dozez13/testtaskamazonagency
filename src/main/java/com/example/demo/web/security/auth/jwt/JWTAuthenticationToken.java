package com.example.demo.web.security.auth.jwt;


import com.example.demo.web.security.model.UserAuthenticationInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private String token;

    private UserAuthenticationInfo authenticationInfo;

    public JWTAuthenticationToken(Collection<? extends GrantedAuthority> authorities,  UserAuthenticationInfo authenticationInfo) {
        super(authorities);
        this.eraseCredentials();
        this.authenticationInfo = authenticationInfo;
        this.setAuthenticated(true);
    }

    public JWTAuthenticationToken(String token) {
        super(null);
        this.token = token;
        this.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.token = null;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.authenticationInfo;
    }
}
