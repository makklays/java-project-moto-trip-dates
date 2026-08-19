package com.techmatrix18.token.application.service;

import com.techmatrix18.token.application.port.in.GetActiveSessionsUseCase;
import com.techmatrix18.token.application.port.out.TokenRepository;
import com.techmatrix18.token.application.query.GetActiveSessionsQuery;
import com.techmatrix18.token.domain.model.Token;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * GetActiveSessionsService
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Service
public class GetActiveSessionsService implements GetActiveSessionsUseCase {

    private final TokenRepository tokenRepository;

    public GetActiveSessionsService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Token> execute(GetActiveSessionsQuery query) { // Исправлено: имя метода и правильный тип Query
        if (query.userId() == null) {
            return List.of();
        }

        // Передаем ID пользователя в репозиторий для поиска только активных сессий
        return tokenRepository.findAllActiveByUserId(query.userId());
    }
}

