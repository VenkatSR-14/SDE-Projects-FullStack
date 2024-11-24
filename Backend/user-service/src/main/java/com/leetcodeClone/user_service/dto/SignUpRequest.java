package com.leetcodeClone.user_service.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Full name is required")
    private String fullname;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Size(max = 128, message = "Region must not exceed 128 characters")
    private String region;

    @Size(max = 550, message = "Bio must not exceed 550 characters")
    private String bio;
}
