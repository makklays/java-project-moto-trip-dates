package com.techmatrix18.rider.application.command;

/**
 * Command to update active rider profile characteristics.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record UpdateRiderProfileCommand(
        Long id,
        Long userId, // Для проверки инварианта безопасности в сервисе
        String nickname,
        Integer ridingSinceYear,
        String drivingStyle,
        String riderType,
        boolean hasHelmetForPassenger,
        String bloodType,
        String bio
) {}

