package com.example.demo.web.security.auth.ajax;


import com.example.demo.core.service.UserService;
import com.example.demo.domain.document.User;
import com.example.demo.web.security.model.UserAuthenticationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component("loginProvider")
@RequiredArgsConstructor
public class AuthenticationLoginProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User userToAuthenticate = Optional.ofNullable(userService.findUserByEmail(email)).orElseThrow(() -> new BadCredentialsException("Email or password is incorrect"));
        if (!passwordEncoder.matches(password, userToAuthenticate.getPassword())) {
            throw new BadCredentialsException("Email or password is incorrect");
        }
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(userToAuthenticate.getUserRole().name()));
        UserAuthenticationInfo authenticationInfo = new UserAuthenticationInfo(userToAuthenticate.getEmail(),userToAuthenticate.getUserRole().name(),  grantedAuthorities);


        return new UsernamePasswordAuthenticationToken(authenticationInfo, null, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
