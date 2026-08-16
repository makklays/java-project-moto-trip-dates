package com.techmatrix18.rider.infrastructure.db;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * Technical database entity mapping the Rider aggregate to the "riders" table.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Entity
@Table(name = "riders")
public class RiderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "nickname", length = 100)
    private String nickname;

    @Column(name = "riding_since_year")
    private Integer ridingSinceYear;

    @Column(name = "driving_style", length = 50)
    private String drivingStyle;

    @Column(name = "rider_type", length = 50)
    private String riderType;

    @Column(name = "has_helmet_for_passenger", nullable = false)
    private boolean hasHelmetForPassenger;

    @Column(name = "blood_type", length = 10)
    private String bloodType;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public Integer getRidingSinceYear() { return ridingSinceYear; }
    public void setRidingSinceYear(Integer ridingSinceYear) { this.ridingSinceYear = ridingSinceYear; }

    public String getDrivingStyle() { return drivingStyle; }
    public void setDrivingStyle(String drivingStyle) { this.drivingStyle = drivingStyle; }

    public String getRiderType() { return riderType; }
    public void setRiderType(String riderType) { this.riderType = riderType; }

    public boolean isHasHelmetForPassenger() { return hasHelmetForPassenger; }
    public void setHasHelmetForPassenger(boolean hasHelmetForPassenger) { this.hasHelmetForPassenger = hasHelmetForPassenger; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}

