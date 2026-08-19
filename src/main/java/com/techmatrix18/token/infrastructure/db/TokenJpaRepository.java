package com.techmatrix18.token.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * TokenJpaRepository
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public interface TokenJpaRepository extends JpaRepository<TokenEntity, Long> {
    // 1. Поиск токена сброса пароля (используется в GetPasswordResetTokenUseCase)
    Optional<TokenEntity> findByPasswordResetToken(String passwordResetToken);

    // 2. Список активных сессий пользователя (используется в GetActiveSessionsUseCase)
    // Находит токены, которые НЕ отозваны (revoked = false)
    List<TokenEntity> findAllByUserIdAndRevokedFalse(Long userId);

    // 3. Поиск токенов по IP-адресу с возможностью фильтрации только активных
    // Используем JPQL для гибкой фильтрации флага onlyActive
    @Query("SELECT t FROM TokenEntity t WHERE t.ipAddress = :ipAddress " +
            "AND (:onlyActive = false OR t.revoked = false)")
    List<TokenEntity> findByIpAddressAndActivity(
            @Param("ipAddress") String ipAddress,
            @Param("onlyActive") boolean onlyActive
    );

    // 4. Дополнительный полезный метод для проверки валидности Access Token
    Optional<TokenEntity> findByToken(String token);

    Optional<TokenEntity> findByRefreshToken(String refreshToken);

    @Modifying // Указывает Spring, что запрос изменяет данные (UPDATE/DELETE)
    @Query("UPDATE TokenEntity t SET t.revoked = true, t.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE t.userId = :userId AND t.revoked = false")
    void revokeAllByUserId(@Param("userId") Long userId);
}

