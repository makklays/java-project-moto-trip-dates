package com.techmatrix18.rider.domain.model;

import java.time.Instant;
import java.time.Year;

/**
 * Rider domain entity containing advanced motorcycle profile tracking attributes,
 * experience details, and active driving statuses.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 15.08.2026
 */

public class Rider {
    private final Long id;
    private final Long userId;

    private String nickname;
    private Integer ridingSinceYear;
    private String drivingStyle;
    private String riderType;
    private boolean hasHelmetForPassenger;
    private String bloodType;
    private String bio;
    private String status;

    private final Instant createdAt;
    private Instant updatedAt;

    // Закрытый конструктор — создание объекта строго через Builder
    private Rider(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.nickname = builder.nickname;
        this.ridingSinceYear = builder.ridingSinceYear;
        this.drivingStyle = builder.drivingStyle;
        this.riderType = builder.riderType;
        this.hasHelmetForPassenger = builder.hasHelmetForPassenger != null ? builder.hasHelmetForPassenger : false;
        this.bloodType = builder.bloodType;
        this.bio = builder.bio;
        this.status = builder.status != null ? builder.status : "PLANNING";
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
    public String getNickname() { return nickname; }
    public Integer getRidingSinceYear() { return ridingSinceYear; }
    public String getDrivingStyle() { return drivingStyle; }
    public String getRiderType() { return riderType; }
    public boolean isHasHelmetForPassenger() { return hasHelmetForPassenger; }
    public String getBloodType() { return bloodType; }
    public String getBio() { return bio; }
    public String getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // --- DOMAIN BEHAVIOR METHODS (Бизнес-логика изменения состояния) ---

    /**
     * Updates full motorcycle experience profile attributes.
     */
    public void updateProfile(String nickname, Integer ridingSinceYear, String drivingStyle,
                              String riderType, boolean hasHelmetForPassenger, String bloodType, String bio) {
        this.nickname = nickname;
        this.ridingSinceYear = ridingSinceYear;
        this.drivingStyle = drivingStyle;
        this.riderType = riderType;
        this.hasHelmetForPassenger = hasHelmetForPassenger;
        this.bloodType = bloodType;
        this.bio = bio;
        this.updatedAt = Instant.now();
    }

    /**
     * Dynamically calculates current riding experience in years based on active system date.
     */
    public int calculateExperienceYears() {
        if (this.ridingSinceYear == null) {
            return 0;
        }
        int currentYear = Year.now().getValue();
        return Math.max(0, currentYear - this.ridingSinceYear);
    }

    /**
     * Updates current riding profile season status.
     * Allowed transitions from: PLANNING -> ACTIVE -> FINISHED
     */
    public void updateStatus(String newStatus) {
        if (newStatus == null || newStatus.isBlank()) {
            throw new IllegalArgumentException("Rider status cannot be empty");
        }
        this.status = newStatus.toUpperCase();
        this.updatedAt = Instant.now();
    }

    // --- STATIC INNER BUILDER ---
    public static class Builder {
        private Long id;
        private Long userId;
        private String nickname;
        private Integer ridingSinceYear;
        private String drivingStyle;
        private String riderType;
        private Boolean hasHelmetForPassenger;
        private String bloodType;
        private String bio;
        private String status;
        private Instant createdAt;
        private Instant updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder nickname(String nickname) { this.nickname = nickname; return this; }
        public Builder ridingSinceYear(Integer ridingSinceYear) { this.ridingSinceYear = ridingSinceYear; return this; }
        public Builder drivingStyle(String drivingStyle) { this.drivingStyle = drivingStyle; return this; }
        public Builder riderType(String riderType) { this.riderType = riderType; return this; }
        public Builder hasHelmetForPassenger(Boolean hasHelmetForPassenger) { this.hasHelmetForPassenger = hasHelmetForPassenger; return this; }
        public Builder bloodType(String bloodType) { this.bloodType = bloodType; return this; }
        public Builder bio(String bio) { this.bio = bio; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder createdAt(Instant createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(Instant updatedAt) { this.updatedAt = updatedAt; return this; }

        public Rider build() {
            if (this.userId == null) {
                throw new IllegalStateException("Domain validation failed: User ID cannot be null");
            }
            return new Rider(this);
        }
    }
}

