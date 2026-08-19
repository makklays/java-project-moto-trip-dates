package com.techmatrix18.token.infrastructure.http;

import com.techmatrix18.token.domain.model.Token;
import java.time.Instant;

/**
 * PasswordResetTokenResponse
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public record PasswordResetTokenResponse(
        String passwordResetToken,
        Long userId,
        Instant expiredPasswordResetToken,
        boolean isExpired
) {
    public static PasswordResetTokenResponse fromDomain(Token domain) {
        boolean expired = domain.getExpiredPasswordResetToken() != null
                && domain.getExpiredPasswordResetToken().isBefore(Instant.now());

        return new PasswordResetTokenResponse(
                domain.getPasswordResetToken(),
                domain.getUserId(),
                domain.getExpiredPasswordResetToken(),
                expired
        );
    }
}

