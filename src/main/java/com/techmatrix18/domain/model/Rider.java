package com.techmatrix18.domain.model;

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

    /**
     * All-args constructor for initializing domain object from persistence layer or factory.
     */
    public Rider(Long id, Long userId, String nickname, Integer ridingSinceYear,
                 String drivingStyle, String riderType, Boolean hasHelmetForPassenger,
                 String bloodType, String bio, String status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.ridingSinceYear = ridingSinceYear;
        this.drivingStyle = drivingStyle;
        this.riderType = riderType;
        this.hasHelmetForPassenger = hasHelmetForPassenger != null ? hasHelmetForPassenger : false;
        this.bloodType = bloodType;
        this.bio = bio;
        this.status = status != null ? status : "PLANNING";
        this.createdAt = createdAt != null ? createdAt : Instant.now();
        this.updatedAt = updatedAt != null ? updatedAt : Instant.now();
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
}

