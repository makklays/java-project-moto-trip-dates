package com.techmatrix18.user.infrastructure.http;

import com.techmatrix18.user.domain.model.DatingStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * CreateRiderRequest
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public record RegisterUserRequest(
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    String username,

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 200, message = "Email must not exceed 200 characters")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    String password,

    @Size(max = 20, message = "Mobile number must not exceed 20 characters")
    String mobile,

    @Size(max = 100, message = "Nickname must not exceed 100 characters")
    String nickname,

    @Size(max = 20, message = "Gender must not exceed 20 characters")
    String gender,

    @NotNull(message = "Birth date is required")
    LocalDate birthDate,

    String bio,

    DatingStatus datingStatus
) {}

