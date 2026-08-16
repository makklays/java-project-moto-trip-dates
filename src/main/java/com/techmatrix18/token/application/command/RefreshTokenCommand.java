package com.techmatrix18.token.application.command;

/**
 * Command to refresh an existing session using a valid refresh token.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record RefreshTokenCommand(
        String refreshToken,
        String ipAddress,
        String userAgent
) {}

