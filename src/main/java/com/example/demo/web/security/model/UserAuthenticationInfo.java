package com.example.demo.web.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


@RequiredArgsConstructor
@Getter
public class UserAuthenticationInfo {

    private final String email;

    private final String role;

    private final List<GrantedAuthority> grantedAuthorities;
}
