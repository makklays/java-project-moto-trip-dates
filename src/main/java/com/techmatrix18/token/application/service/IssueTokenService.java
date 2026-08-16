package com.techmatrix18.token.application.service;

import com.techmatrix18.token.application.command.IssueTokenCommand;
import com.techmatrix18.token.application.port.in.IssueTokenUseCase;
import com.techmatrix18.token.application.port.out.TokenRepository;
import com.techmatrix18.token.domain.model.Token;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator that handles issuing a new authentication token pair.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class IssueTokenService implements IssueTokenUseCase {

    private final TokenRepository tokenRepository;

    public IssueTokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    @Transactional
    public Token execute(IssueTokenCommand command) {
        // Сборка новой сессии через канонический доменный Builder
        Token tokenSession = Token.builder()
                .userId(command.userId())
                .token(command.token())
                .expiredToken(command.expiredToken())
                .refreshToken(command.refreshToken())
                .expiredRefreshToken(command.expiredRefreshToken())
                .ipAddress(command.ipAddress())
                .userAgent(command.userAgent())
                .revoked(false)
                .build();

        return tokenRepository.save(tokenSession);
    }
}

