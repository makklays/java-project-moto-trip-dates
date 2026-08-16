package com.techmatrix18.rider.infrastructure.http;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * CreateRiderRequest
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record CreateRiderRequest(
        @NotNull(message = "User ID cannot be null") Long userId,
        @NotBlank(message = "Nickname cannot be empty") String nickname,
        Integer ridingSinceYear,
        String drivingStyle,
        String riderType,
        boolean hasHelmetForPassenger,
        String bloodType,
        String bio
) {}

