package com.techmatrix18.token.application.command;

import java.time.Instant;

/**
 * Command to issue a new authentication token pair for a user session.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record IssueTokenCommand(
    Long userId,
    String token,
    Instant expiredToken,
    String refreshToken,
    Instant expiredRefreshToken,
    String ipAddress,
    String userAgent
) {}
