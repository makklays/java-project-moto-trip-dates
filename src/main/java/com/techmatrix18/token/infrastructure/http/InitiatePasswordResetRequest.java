package com.techmatrix18.token.infrastructure.http;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * InitiatePasswordResetRequest
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public record InitiatePasswordResetRequest(
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email
) {}

