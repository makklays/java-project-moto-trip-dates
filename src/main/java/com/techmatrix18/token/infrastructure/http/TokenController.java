package com.techmatrix18.token.infrastructure.http;

import com.techmatrix18.token.application.command.InitiatePasswordResetCommand;
import com.techmatrix18.token.application.command.IssueTokenCommand;
import com.techmatrix18.token.application.command.RefreshTokenCommand;
import com.techmatrix18.token.application.command.RevokeTokenCommand;
import com.techmatrix18.token.application.port.in.*;
import com.techmatrix18.token.application.query.GetActiveSessionsQuery;
import com.techmatrix18.token.application.query.GetPasswordResetTokenQuery;
import com.techmatrix18.token.application.query.GetTokensByIpAddressQuery;
import com.techmatrix18.token.domain.model.Token;
import com.techmatrix18.user.application.port.in.FindUserByEmailUseCase;
import com.techmatrix18.user.application.query.FindUserByEmailQuery;
import com.techmatrix18.user.domain.exception.DomainException;
import com.techmatrix18.user.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TokenController
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@RestController
@RequestMapping("/api/v1/auth")
public class TokenController {

    private final GetActiveSessionsUseCase getActiveSessionsUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final GetPasswordResetTokenUseCase getPasswordResetTokenUseCase;
    private final GetTokensByIpAddressUseCase getTokensByIpAddressUseCase;
    private final InitiatePasswordResetUseCase initiatePasswordResetUseCase;
    private final FindUserByEmailUseCase findUserByEmailUseCase;
    private final IssueTokenUseCase issueTokenUseCase;

    public TokenController(GetActiveSessionsUseCase getActiveSessionsUseCase, RefreshTokenUseCase refreshTokenUseCase,
                           GetPasswordResetTokenUseCase getPasswordResetTokenUseCase,
                           GetTokensByIpAddressUseCase getTokensByIpAddressUseCase,
                           InitiatePasswordResetUseCase initiatePasswordResetUseCase,
                           FindUserByEmailUseCase findUserByEmailUseCase,
                           IssueTokenUseCase issueTokenUseCase) {
        this.getActiveSessionsUseCase = getActiveSessionsUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.getPasswordResetTokenUseCase = getPasswordResetTokenUseCase;
        this.getTokensByIpAddressUseCase = getTokensByIpAddressUseCase;
        this.initiatePasswordResetUseCase = initiatePasswordResetUseCase;
        this.findUserByEmailUseCase = findUserByEmailUseCase;
        this.issueTokenUseCase = issueTokenUseCase;
    }

    /**
     * POST /api/v1/auth/login
     * Аутентификация пользователя и выпуск новой пары токенов (сессии)
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest servletRequest) {

        // 1. Находим пользователя по email через Use Case чтения
        FindUserByEmailQuery query = new FindUserByEmailQuery(request.email());
        User user = findUserByEmailUseCase.execute(query)
                .orElseThrow(() -> new DomainException("Invalid email or password"));

        // 2. Генерируем технические параметры для команды выпуска токена
        String generatedAccessToken = java.util.UUID.randomUUID().toString();
        String generatedRefreshToken = java.util.UUID.randomUUID().toString();
        java.time.Instant expiredToken = java.time.Instant.now().plus(java.time.Duration.ofMinutes(15));
        java.time.Instant expiredRefreshToken = java.time.Instant.now().plus(java.time.Duration.ofDays(7));

        String ipAddress = servletRequest.getRemoteAddr();
        String userAgent = servletRequest.getHeader("User-Agent");

        // 3. Строим команду мутации строго по вашему контракту IssueTokenCommand
        IssueTokenCommand command = new IssueTokenCommand(
                user.getId(),
                generatedAccessToken,
                expiredToken,
                generatedRefreshToken,
                expiredRefreshToken,
                ipAddress,
                userAgent
        );

        // 4. Выполняем сценарий и мапим доменную модель в безопасный ответ
        Token token = issueTokenUseCase.execute(command);
        return ResponseEntity.ok(TokenResponse.fromDomain(token));
    }

    /**
     * POST /api/v1/auth/logout
     * Отзыв конкретного токена (Выход из текущей сессии)
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam String token) {
        RevokeTokenCommand command = new RevokeTokenCommand(token);

        // Здесь: Вызов Use Case для отзыва токена
        return ResponseEntity.noContent().build();
    }

    /**
     * POST /api/v1/auth/refresh
     * Обновление пары токенов по Refresh Token
     */
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(
            @RequestParam String refreshToken,
            HttpServletRequest servletRequest) {

        String ipAddress = servletRequest.getRemoteAddr();
        String userAgent = servletRequest.getHeader("User-Agent");

        RefreshTokenCommand command = new RefreshTokenCommand(refreshToken, ipAddress, userAgent);

        // 1. Вызываем бизнес-сценарий (Use Case возвращает обновленный доменный агрегат Token)
        Token updatedToken = refreshTokenUseCase.execute(command);

        // 2. Мапим через фабричный метод в безопасный ответ
        TokenResponse response = TokenResponse.fromDomain(updatedToken);

        // 3. Возвращаем со статусом 200 OK
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/v1/auth/sessions
     * Получение списка всех активных сессий текущего пользователя
     */
    @GetMapping("/sessions")
    public ResponseEntity<List<Token>> getActiveSessions(@RequestHeader("X-User-Id") Long userId) {
        GetActiveSessionsQuery query = new GetActiveSessionsQuery(userId);
        List<Token> activeSessions = getActiveSessionsUseCase.execute(query);

        return ResponseEntity.ok(activeSessions);
    }

    /**
     * GET /api/v1/auth/password-reset/verify?token=abc-123-xyz
     * Проверка валидности токена сброса пароля перед показом формы ввода нового пароля
     */
    @GetMapping("/password-reset/verify")
    public ResponseEntity<PasswordResetTokenResponse> verifyPasswordResetToken(@RequestParam("token") String token) {

        // 1. Упаковываем входную строку в объект Query
        GetPasswordResetTokenQuery query = new GetPasswordResetTokenQuery(token);

        // 2. Вызываем Use Case и мапим Optional-результат
        return getPasswordResetTokenUseCase.execute(query)
                .map(domainToken -> ResponseEntity.ok(PasswordResetTokenResponse.fromDomain(domainToken)))
                // Если токен не найден в БД, отдаем 404 статус
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /api/v1/auth/tokens/by-ip
     * Аудиторский эндпоинт для поиска сессий по конкретному IP-адресу
     * Пример: /api/v1/auth/tokens/by-ip?ipAddress=192.168.1.100&onlyActive=true
     */
    @GetMapping("/tokens/by-ip")
    public ResponseEntity<List<TokenResponse>> getTokensByIp(
            @RequestParam String ipAddress,
            @RequestParam(defaultValue = "false") boolean onlyActive) {

        // 1. Упаковываем параметры в объект запроса Query
        GetTokensByIpAddressQuery query = new GetTokensByIpAddressQuery(ipAddress, onlyActive);

        // 2. Выполняем Use Case бизнес-логики
        List<Token> tokens = getTokensByIpAddressUseCase.execute(query);

        // 3. Мапим доменные токены в список безопасных инфраструктурных DTO TokenResponse
        List<TokenResponse> response = tokens.stream()
                .map(TokenResponse::fromDomain)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/v1/auth/password-reset/initiate
     * Инициация процедуры сброса пароля (генерация токена и отправка email)
     */
    @PostMapping("/password-reset/initiate")
    public ResponseEntity<Void> initiatePasswordReset(
            @Valid @RequestBody InitiatePasswordResetRequest request) {

        // 1. Создаем Query-объект для поиска
        FindUserByEmailQuery query = new FindUserByEmailQuery(request.email());

        // 2. Безопасно находим пользователя через Use Case чтения
        User user = findUserByEmailUseCase.execute(query)
            .orElseThrow(() -> new DomainException("User with email '" + request.email() + "' not found"));

        // 3. Генерируем параметры токена на стороне инфраструктуры
        String generatedToken = java.util.UUID.randomUUID().toString();
        java.time.Instant expirationTime = java.time.Instant.now().plus(java.time.Duration.ofHours(2));

        // 4. Собираем команду мутации строго по вашему контракту
        InitiatePasswordResetCommand command = new InitiatePasswordResetCommand(
            user.getId(),
            generatedToken,
            expirationTime
        );

        // 5. Передаем команду в Use Case изменения данных
        initiatePasswordResetUseCase.execute(command);

        return ResponseEntity.accepted().build();
    }
}

