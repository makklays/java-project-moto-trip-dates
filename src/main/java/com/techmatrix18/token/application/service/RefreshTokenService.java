package com.techmatrix18.token.application.service;

import com.techmatrix18.token.application.command.RefreshTokenCommand;
import com.techmatrix18.token.application.port.in.RefreshTokenUseCase;
import com.techmatrix18.token.application.port.out.TokenRepository;
import com.techmatrix18.token.domain.model.Token;
import com.techmatrix18.user.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Service orchestrator that validates and refreshes an expired authentication session.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class RefreshTokenService implements RefreshTokenUseCase {

    private final TokenRepository tokenRepository;

    public RefreshTokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    @Transactional
    public Token execute(RefreshTokenCommand command) {
        // 1. Ищем сессию по refresh-токену
        Token oldSession = tokenRepository.findByRefreshToken(command.refreshToken())
                .orElseThrow(() -> new DomainException("Invalid refresh token"));

        // 2. Защита инвариантов: токен не должен быть отозван или просрочен
        if (oldSession.isRevoked()) {
            throw new DomainException("Token session has been revoked");
        }
        if (oldSession.isRefreshTokenExpired()) {
            throw new DomainException("Refresh token has expired. Please log in again");
        }

        // 3. Отзываем старую сессию (мутация Rich домена)
        oldSession.revoke();
        tokenRepository.save(oldSession);

        // 4. Генерируем новые токены (в будущем логика переедет в JwtProvider)
        String newAccessToken = UUID.randomUUID().toString();
        String newRefreshToken = UUID.randomUUID().toString();

        Token newSession = Token.builder()
                .userId(oldSession.getUserId())
                .token(newAccessToken)
                .expiredToken(Instant.now().plus(15, ChronoUnit.MINUTES))
                .refreshToken(newRefreshToken)
                .expiredRefreshToken(Instant.now().plus(7, ChronoUnit.DAYS))
                .ipAddress(command.ipAddress())
                .userAgent(command.userAgent())
                .build();

        return tokenRepository.save(newSession);
    }
}

