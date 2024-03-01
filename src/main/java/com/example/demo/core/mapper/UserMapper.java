package com.example.demo.core.mapper;

import com.example.demo.core.dto.UserRegistrationDto;
import com.example.demo.domain.document.User;
import com.example.demo.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User fromUserRegistrationDto(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setUserRole(UserRole.USER);
        return user;
    }
}
