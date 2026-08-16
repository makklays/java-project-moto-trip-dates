package com.techmatrix18.rider.application.command;

/**
 * Command to initialize a new rider profile linked to a user account.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record CreateRiderProfileCommand(
        Long userId,
        String nickname,
        Integer ridingSinceYear,
        String drivingStyle,
        String riderType,
        boolean hasHelmetForPassenger,
        String bloodType,
        String bio,
        String status
) {}

