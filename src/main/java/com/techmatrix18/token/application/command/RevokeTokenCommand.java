package com.techmatrix18.token.application.command;

/**
 * Command to revoke an active token session (Logout).
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record RevokeTokenCommand(
        String token
) {}

