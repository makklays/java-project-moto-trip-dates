package com.techmatrix18.user.application.command;

import java.time.LocalDate;

/**
 * RegisterUserCommand - Immutable command cargo carrying profile
 * and authentication data for new user registration.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

public record RegisterUserCommand(
    String username,
    String email,
    String password,
    String mobile,
    String nickname,
    String gender,
    Integer age,
    LocalDate birthDate,
    String bio,
    String datingStatus
) {}

