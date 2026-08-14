package com.techmatrix18.domain.model;

import java.time.Instant;
import java.time.LocalDate;

/**
 * User domain entity containing authentication details and
 * motorcycle dating profile attributes.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 14.08.2026
 */

public class User {
    private final Long id;
    private String username;
    private final String email;
    private String baseRole;
    private String mobile;

    // Data for dates
    private String nickname;
    private String gender;
    private Integer age;
    private String avatarUrl;
    private LocalDate birthDate;
    private String bio;
    private String datingStatus;

    private String password;

    private final Instant createdAt;
    private Instant updatedAt;

    /**
     * All-args constructor for initializing domain object from persistence layer or factory.
     */
    public User(Long id, String username, String email, String baseRole, String mobile,
                String nickname, String gender, Integer age, String avatarUrl,
                LocalDate birthDate, String bio, String datingStatus, String password,
                Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.baseRole = baseRole != null ? baseRole : "USER";
        this.mobile = mobile;
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.avatarUrl = avatarUrl;
        this.birthDate = birthDate;
        this.bio = bio;
        this.datingStatus = datingStatus != null ? datingStatus : "DRIVER";
        this.password = password;
        this.createdAt = createdAt != null ? createdAt : Instant.now();
        this.updatedAt = updatedAt != null ? updatedAt : Instant.now();
    }

    // --- GETTERS ---
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getBaseRole() { return baseRole; }
    public String getMobile() { return mobile; }
    public String getNickname() { return nickname; }
    public String getGender() { return gender; }
    public Integer getAge() { return age; }
    public String getAvatarUrl() { return avatarUrl; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBio() { return bio; }
    public String getDatingStatus() { return datingStatus; }
    public String getPassword() { return password; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getOriginalUpdatedAt() { return updatedAt; }

    // --- DOMAIN BEHAVIOR METHODS (Бизнес-логика изменения состояния) ---

    /**
     * Updates profile data related to motorcycle dating.
     */
    public void updateDatingProfile(String nickname, String gender, Integer age,
                                    LocalDate birthDate, String bio, String datingStatus) {
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.birthDate = birthDate;
        this.bio = bio;
        this.datingStatus = datingStatus != null ? datingStatus : "DRIVER";
        this.updatedAt = Instant.now();
    }

    /**
     * Updates account avatar URL after uploading to storage.
     */
    public void updateAvatar(String newAvatarUrl) {
        this.avatarUrl = newAvatarUrl;
        this.updatedAt = Instant.now();
    }

    /**
     * Updates password with new hash value.
     */
    public void changePassword(String newPasswordHash) {
        this.password = newPasswordHash;
        this.updatedAt = Instant.now();
    }

    /**
     * Checks if the user is a motorcycle driver.
     */
    public boolean isDriver() {
        return "DRIVER".equalsIgnoreCase(this.datingStatus);
    }
}

