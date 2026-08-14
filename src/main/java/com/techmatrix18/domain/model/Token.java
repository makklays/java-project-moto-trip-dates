package com.techmatrix18.domain.model;

import java.time.Instant;

/**
 * Token domain entity managing authentication sessions,
 * access/refresh token lifecycles, and security tracking data.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 14.08.2026
 */

public class Token {
    private final Long id;
    private final Long userId;

    private final String token;
    private final Instant expiredToken;

    private final String refreshToken;
    private final Instant expiredRefreshToken;

    private String passwordResetToken;
    private Instant expiredPasswordResetToken;

    private final String ipAddress;
    private final String userAgent;
    private boolean revoked;

    private final Instant createdAt;
    private Instant updatedAt;

    /**
     * All-args constructor for initializing domain object from persistence layer or factory.
     */
    public Token(Long id, Long userId, String token, Instant expiredToken,
                 String refreshToken, Instant expiredRefreshToken,
                 String passwordResetToken, Instant expiredPasswordResetToken,
                 String ipAddress, String userAgent, Boolean revoked,
                 Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.expiredToken = expiredToken;
        this.refreshToken = refreshToken;
        this.expiredRefreshToken = expiredRefreshToken;
        this.passwordResetToken = passwordResetToken;
        this.expiredPasswordResetToken = expiredPasswordResetToken;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.revoked = revoked != null ? revoked : false;
        this.createdAt = createdAt != null ? createdAt : Instant.now();
        this.updatedAt = updatedAt != null ? updatedAt : Instant.now();
    }

    // --- GETTERS ---
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getToken() { return token; }
    public Instant getExpiredToken() { return expiredToken; }
    public String getRefreshToken() { return refreshToken; }
    public Instant getExpiredRefreshToken() { return expiredRefreshToken; }
    public String getPasswordResetToken() { return passwordResetToken; }
    public Instant getExpiredPasswordResetToken() { return expiredPasswordResetToken; }
    public String getIpAddress() { return ipAddress; }
    public String getUserAgent() { return userAgent; }
    public boolean isRevoked() { return revoked; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // --- DOMAIN BEHAVIOR METHODS (Бизнес-логика изменения состояния) ---

    /**
     * Revokes current token session making it invalid for further usage.
     */
    public void revoke() {
        this.revoked = true;
        this.updatedAt = Instant.now();
    }

    /**
     * Checks if access token has expired based on current timestamp.
     */
    public boolean isAccessTokenExpired() {
        return Instant.now().isAfter(this.expiredToken);
    }

    /**
     * Checks if refresh token has expired based on current timestamp.
     */
    public boolean isRefreshTokenExpired() {
        return Instant.now().isAfter(this.expiredRefreshToken);
    }

    /**
     * Sets password reset details and updates modification timestamp.
     */
    public void initiatePasswordReset(String resetToken, Instant expirationTime) {
        this.passwordResetToken = resetToken;
        this.expiredPasswordResetToken = expirationTime;
        this.updatedAt = Instant.now();
    }

    /**
     * Clears password reset tokens after successful password change operation.
     */
    public void completePasswordReset() {
        this.passwordResetToken = null;
        this.expiredPasswordResetToken = null;
        this.updatedAt = Instant.now();
    }
}

