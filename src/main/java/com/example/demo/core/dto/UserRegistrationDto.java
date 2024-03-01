package com.example.demo.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationDto {
    private String email;

    private String firstName;

    private String lastName;

    private String password;
}
