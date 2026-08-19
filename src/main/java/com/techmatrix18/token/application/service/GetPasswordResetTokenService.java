package com.techmatrix18.token.application.service;

import com.techmatrix18.token.application.port.in.GetPasswordResetTokenUseCase;
import com.techmatrix18.token.application.query.GetPasswordResetTokenQuery;
import com.techmatrix18.token.application.port.out.TokenRepository;
import com.techmatrix18.token.domain.model.Token;
import com.techmatrix18.user.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service orchestrator executing password reset token lookup.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Service
public class GetPasswordResetTokenService implements GetPasswordResetTokenUseCase {

    private final TokenRepository tokenRepository;

    public GetPasswordResetTokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Token> execute(GetPasswordResetTokenQuery query) {
        // Превентивная валидация инварианта
        if (query.passwordResetToken() == null || query.passwordResetToken().isBlank()) {
            throw new DomainException("Password reset token parameter cannot be null or empty");
        }

        // Запрашиваем токен через выходной порт инфраструктуры
        return tokenRepository.findByPasswordResetToken(query.passwordResetToken());
    }
}

