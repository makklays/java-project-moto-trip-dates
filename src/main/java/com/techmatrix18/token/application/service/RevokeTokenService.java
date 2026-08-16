package com.techmatrix18.token.application.service;

import com.techmatrix18.token.application.command.RevokeTokenCommand;
import com.techmatrix18.token.application.port.in.RevokeTokenUseCase;
import com.techmatrix18.token.application.port.out.TokenRepository;
import com.techmatrix18.token.domain.model.Token;
import com.techmatrix18.user.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator handling user logout by revoking token session.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class RevokeTokenService implements RevokeTokenUseCase {

    private final TokenRepository tokenRepository;

    public RevokeTokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    @Transactional
    public void execute(RevokeTokenCommand command) {
        Token session = tokenRepository.findByToken(command.token())
                .orElseThrow(() -> new DomainException("Active token session not found"));

        // Изменяем состояние домена через инкапсулированный метод поведения
        session.revoke();

        tokenRepository.save(session);
    }
}

