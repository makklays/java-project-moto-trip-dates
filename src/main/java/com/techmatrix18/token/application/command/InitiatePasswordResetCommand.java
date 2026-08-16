package com.techmatrix18.token.application.command;

import java.time.Instant;

/**
 * Command to generate a password reset token for a user.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record InitiatePasswordResetCommand(
        Long userId,
        String passwordResetToken,
        Instant expirationTime
) {}

