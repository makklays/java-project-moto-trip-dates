package com.techmatrix18.token.application.service;

import com.techmatrix18.token.application.port.in.GetTokensByIpAddressUseCase;
import com.techmatrix18.token.application.port.out.TokenRepository;
import com.techmatrix18.token.application.query.GetTokensByIpAddressQuery;
import com.techmatrix18.token.domain.model.Token;
import com.techmatrix18.user.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * GetTokensByIpAddressService
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Service
public class GetTokensByIpAddressService implements GetTokensByIpAddressUseCase {
    private final TokenRepository tokenRepository;

    public GetTokensByIpAddressService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Token> execute(GetTokensByIpAddressQuery query) {
        // Простая превентивная валидация инварианта
        if (query.ipAddress() == null || query.ipAddress().isBlank()) {
            throw new DomainException("IP address parameter cannot be null or empty");
        }

        // Делегируем выборку выходному порту инфраструктуры
        return tokenRepository.findByIpAddress(query.ipAddress(), query.onlyActive());
    }
}

