package com.techmatrix18.rider.infrastructure.http;

import jakarta.validation.constraints.NotBlank;

/**
 * UpdateRiderRequest
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record UpdateRiderRequest(
        @NotBlank(message = "Nickname cannot be empty") String nickname,
        Integer ridingSinceYear,
        String drivingStyle,
        String riderType,
        boolean hasHelmetForPassenger,
        String bloodType,
        String bio
) {}

