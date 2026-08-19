package com.techmatrix18.token.infrastructure.db;

import com.techmatrix18.token.application.port.out.TokenRepository;
import com.techmatrix18.token.domain.model.Token;
import com.techmatrix18.token.infrastructure.mapper.TokenMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TokenRepositoryAdapter
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Component
public class TokenRepositoryAdapter implements TokenRepository {
    private final TokenJpaRepository jpaRepository;
    private final TokenMapper tokenMapper; // Маппер для конвертации между слоями домена и БД

    public TokenRepositoryAdapter(TokenJpaRepository jpaRepository, TokenMapper tokenMapper) {
        this.jpaRepository = jpaRepository;
        this.tokenMapper = tokenMapper;
    }

    @Override
    public List<Token> findByIpAddress(String ipAddress, boolean onlyActive) {
        List<TokenEntity> entities = jpaRepository.findByIpAddressAndActivity(ipAddress, onlyActive);
        return entities.stream()
                .map(tokenMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Token> findAllActiveByUserId(Long userId) {
        List<TokenEntity> entities = jpaRepository.findAllByUserIdAndRevokedFalse(userId);
        return entities.stream()
                .map(tokenMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Token> findByPasswordResetToken(String passwordResetToken) {
        return jpaRepository.findByPasswordResetToken(passwordResetToken)
                .map(tokenMapper::toDomain);
    }

    @Override
    public Optional<Token> findByToken(String token) {
        // 1. Делаем запрос к Spring Data JPA репозиторию
        // 2. Если сущность найдена, MapStruct автоматически преобразует её в доменную модель Token
        return jpaRepository.findByToken(token)
                .map(tokenMapper::toDomain);
    }

    @Override
    public Optional<Token> findByRefreshToken(String refreshToken) {
        // 1. Ищем сущность в JPA репозитории по refresh-токену
        // 2. Трансформируем инфраструктурную сущность в доменную модель Token
        return jpaRepository.findByRefreshToken(refreshToken)
                .map(tokenMapper::toDomain);
    }

    @Override
    public void revokeAllByUserId(Long userId) {
        // Вызываем метод пакетного обновления в JPA репозитории
        jpaRepository.revokeAllByUserId(userId);
    }

    // --- Если в интерфейсе TokenRepository объявлены базовые методы сохранения/удаления, реализуем их: ---

    public Token save(Token token) {
        TokenEntity entity = tokenMapper.toEntity(token);
        TokenEntity savedEntity = jpaRepository.save(entity);
        return tokenMapper.toDomain(savedEntity);
    }

    public Optional<Token> findById(Long id) {
        return jpaRepository.findById(id)
                .map(tokenMapper::toDomain);
    }
}
