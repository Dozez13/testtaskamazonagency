package com.example.demo.core.service;

import com.example.demo.core.dto.UserRegistrationDto;
import com.example.demo.domain.document.User;
import org.springframework.data.repository.query.Param;

public interface UserService {
    boolean existUserByEmail(String email);

    User findUserByEmail(String email);

    void createUser(UserRegistrationDto userRegistrationDto);
}
