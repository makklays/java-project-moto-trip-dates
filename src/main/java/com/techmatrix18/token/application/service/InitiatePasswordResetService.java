package com.techmatrix18.token.application.service;

import com.techmatrix18.token.application.command.InitiatePasswordResetCommand;
import com.techmatrix18.token.application.port.in.InitiatePasswordResetUseCase;
import com.techmatrix18.token.application.port.out.TokenRepository;
import com.techmatrix18.token.domain.model.Token;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

/**
 * Service orchestrator that creates and binds password reset tokens to user sessions.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class InitiatePasswordResetService implements InitiatePasswordResetUseCase {

    private final TokenRepository tokenRepository;

    public InitiatePasswordResetService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    @Transactional
    public Token execute(InitiatePasswordResetCommand command) {
        // Отзываем все старые сессии пользователя в целях безопасности перед сбросом
        tokenRepository.revokeAllByUserId(command.userId());

        // Создаем техническую сессию, содержащую только данные сброса пароля
        Token resetSession = Token.builder()
                .userId(command.userId())
                .token("RESET_STUB_" + UUID.randomUUID()) // Заглушка, так как поле token обязательное в build()
                .refreshToken("RESET_REF_STUB_" + UUID.randomUUID()) // Заглушка
                .passwordResetToken(command.passwordResetToken())
                .expiredPasswordResetToken(command.expirationTime())
                .build();

        return tokenRepository.save(resetSession);
    }
}

