package com.example.demo.core.service.impl;

import com.example.demo.core.dto.UserRegistrationDto;
import com.example.demo.core.expection.UserAlreadyExistRuntimeException;
import com.example.demo.core.mapper.UserMapper;
import com.example.demo.core.service.MessageService;
import com.example.demo.core.service.UserService;
import com.example.demo.domain.document.User;
import com.example.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MessageService messageService;

    @Override
    public boolean existUserByEmail(String email) {
        return userRepository.existUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void createUser(UserRegistrationDto userRegistrationDto) {
        boolean isUserAlreadyExists = userRepository.existUserByEmail(userRegistrationDto.getEmail());
        if (isUserAlreadyExists) {
            throw new UserAlreadyExistRuntimeException(messageService.getMessage("user.with.such.email.already.exists"));
        }
        User user = userMapper.fromUserRegistrationDto(userRegistrationDto);
        userRepository.save(user);
    }
}
