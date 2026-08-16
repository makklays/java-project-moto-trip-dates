package com.techmatrix18.token.application.port.out;

import com.techmatrix18.token.domain.model.Token;
import java.util.Optional;

/**
 * Outbound port specifying database persistence operations for Token aggregate.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface TokenRepository {

    // Saves or updates a token session aggregate.
    Token save(Token token);

    // Finds active or inactive token details by its primary key.
    Optional<Token> findById(Long id);

    // Finds token session by the active access token string.
    Optional<Token> findByToken(String token);

    // Finds token session by the refresh token string.
    Optional<Token> findByRefreshToken(String refreshToken);

    // Finds token session by the password reset token string.
    Optional<Token> findByPasswordResetToken(String passwordResetToken);

    // Revokes all active sessions for a specific user.
    // Useful for password changes or forced logouts from all devices.
    void revokeAllByUserId(Long userId);
}

