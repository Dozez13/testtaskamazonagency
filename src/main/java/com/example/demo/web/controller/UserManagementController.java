package com.example.demo.web.controller;

import com.example.demo.core.dto.UserRegistrationDto;
import com.example.demo.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserManagementController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        userService.createUser(userRegistrationDto);
    }
}
