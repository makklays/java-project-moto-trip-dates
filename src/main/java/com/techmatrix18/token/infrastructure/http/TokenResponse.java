package com.techmatrix18.token.infrastructure.http;

import com.techmatrix18.token.domain.model.Token;
import java.time.Instant;

/**
 * TokenResponse
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public record TokenResponse(
    String accessToken,
    String tokenType,
    Instant expiredToken,
    String refreshToken,
    Instant expiredRefreshToken
) {
    /**
     * Статический фабричный метод для маппинга доменной модели в HTTP DTO
     */
    public static TokenResponse fromDomain(Token domain) {
        return new TokenResponse(
                domain.getToken(),
                "Bearer", // Стандарт для JWT / Access сессий
                domain.getExpiredToken(),
                domain.getRefreshToken(),
                domain.getExpiredRefreshToken()
        );
    }
}

