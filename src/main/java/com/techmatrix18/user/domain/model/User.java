package com.techmatrix18.user.domain.model;

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

    // Закрытый конструктор — создание объекта теперь строго через Builder
    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.baseRole = builder.baseRole != null ? builder.baseRole : "USER";
        this.mobile = builder.mobile;
        this.nickname = builder.nickname;
        this.gender = builder.gender;
        this.age = builder.age;
        this.avatarUrl = builder.avatarUrl;
        this.birthDate = builder.birthDate;
        this.bio = builder.bio;
        this.datingStatus = builder.datingStatus != null ? builder.datingStatus : "DRIVER";
        this.password = builder.password;
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
    public Instant getUpdatedAt() { return updatedAt; }

    // --- BUSINESS LOGIC METHODS ---

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

    // --- STATIC INNER BUILDER ---
    public static class Builder {
        private Long id;
        private String username;
        private String email;
        private String baseRole;
        private String mobile;
        private String nickname;
        private String gender;
        private Integer age;
        private String avatarUrl;
        private LocalDate birthDate;
        private String bio;
        private String datingStatus;
        private String password;
        private Instant createdAt;
        private Instant updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder username(String username) { this.username = username; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder baseRole(String baseRole) { this.baseRole = baseRole; return this; }
        public Builder mobile(String mobile) { this.mobile = mobile; return this; }
        public Builder nickname(String nickname) { this.nickname = nickname; return this; }
        public Builder gender(String gender) { this.gender = gender; return this; }
        public Builder age(Integer age) { this.age = age; return this; }
        public Builder avatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; return this; }
        public Builder birthDate(LocalDate birthDate) { this.birthDate = birthDate; return this; }
        public Builder bio(String bio) { this.bio = bio; return this; }
        public Builder datingStatus(String datingStatus) { this.datingStatus = datingStatus; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder createdAt(Instant createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(Instant updatedAt) { this.updatedAt = updatedAt; return this; }

        public User build() {
            if (this.email == null || this.email.isBlank()) {
                throw new IllegalStateException("Domain validation failed: Email cannot be null or empty");
            }
            return new User(this);
        }
    }
}

