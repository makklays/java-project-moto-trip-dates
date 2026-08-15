package com.techmatrix18.token.domain.model;

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

    // Закрытый конструктор — создание объекта строго через Builder
    private Token(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.token = builder.token;
        this.expiredToken = builder.expiredToken;
        this.refreshToken = builder.refreshToken;
        this.expiredRefreshToken = builder.expiredRefreshToken;
        this.passwordResetToken = builder.passwordResetToken;
        this.expiredPasswordResetToken = builder.expiredPasswordResetToken;
        this.ipAddress = builder.ipAddress;
        this.userAgent = builder.userAgent;
        this.revoked = builder.revoked != null ? builder.revoked : false;
        this.createdAt = builder.createdAt != null ? builder.createdAt : Instant.now();
        this.updatedAt = builder.updatedAt != null ? builder.updatedAt : Instant.now();
    }

    /**
     * Factory method to initialize a new Builder instance.
     */
    public static Builder builder() {
        return new Builder();
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

    // --- STATIC INNER BUILDER ---
    public static class Builder {
        private Long id;
        private Long userId;
        private String token;
        private Instant expiredToken;
        private String refreshToken;
        private Instant expiredRefreshToken;
        private String passwordResetToken;
        private Instant expiredPasswordResetToken;
        private String ipAddress;
        private String userAgent;
        private Boolean revoked;
        private Instant createdAt;
        private Instant updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder token(String token) { this.token = token; return this; }
        public Builder expiredToken(Instant expiredToken) { this.expiredToken = expiredToken; return this; }
        public Builder refreshToken(String refreshToken) { this.refreshToken = refreshToken; return this; }
        public Builder expiredRefreshToken(Instant expiredRefreshToken) { this.expiredRefreshToken = expiredRefreshToken; return this; }
        public Builder passwordResetToken(String passwordResetToken) { this.passwordResetToken = passwordResetToken; return this; }
        public Builder expiredPasswordResetToken(Instant expiredPasswordResetToken) { this.expiredPasswordResetToken = expiredPasswordResetToken; return this; }
        public Builder ipAddress(String ipAddress) { this.ipAddress = ipAddress; return this; }
        public Builder userAgent(String userAgent) { this.userAgent = userAgent; return this; }
        public Builder revoked(Boolean revoked) { this.revoked = revoked; return this; }
        public Builder createdAt(Instant createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(Instant updatedAt) { this.updatedAt = updatedAt; return this; }

        public Token build() {
            if (this.userId == null) {
                throw new IllegalStateException("Domain validation failed: User ID cannot be null");
            }
            if (this.token == null || this.token.isBlank()) {
                throw new IllegalStateException("Domain validation failed: Token value cannot be empty");
            }
            if (this.refreshToken == null || this.refreshToken.isBlank()) {
                throw new IllegalStateException("Domain validation failed: Refresh token value cannot be empty");
            }
            return new Token(this);
        }
    }
}

