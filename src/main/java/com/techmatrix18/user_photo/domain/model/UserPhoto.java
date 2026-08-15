package com.techmatrix18.user_photo.domain.model;

import java.time.Instant;

/**
 * UserPhoto domain entity managing images for the user's gallery
 * and their display order in the dating feed.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 15.08.2026
 */

public class UserPhoto {
    private final Long id;
    private final Long userId;
    private String photoUrl;
    private int displayOrder;
    private final Instant createdAt;
    private Instant updatedAt;

    // Закрытый конструктор — создание объекта строго через Builder
    private UserPhoto(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.photoUrl = builder.photoUrl;
        this.displayOrder = builder.displayOrder != null ? builder.displayOrder : 0;
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
    public String getPhotoUrl() { return photoUrl; }
    public int getDisplayOrder() { return displayOrder; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // --- DOMAIN BEHAVIOR METHODS (Бизнес-логика изменения состояния) ---

    /**
     * Updates the URL of the photo if it was re-uploaded or replaced in storage.
     */
    public void updatePhotoUrl(String newPhotoUrl) {
        if (newPhotoUrl == null || newPhotoUrl.isBlank()) {
            throw new IllegalArgumentException("Photo URL cannot be empty");
        }
        this.photoUrl = newPhotoUrl;
        this.updatedAt = Instant.now();
    }

    /**
     * Changes the sorting order of the photo in the user's profile gallery.
     */
    public void changeDisplayOrder(int newOrder) {
        if (newOrder < 0) {
            throw new IllegalArgumentException("Display order cannot be negative");
        }
        this.displayOrder = newOrder;
        this.updatedAt = Instant.now();
    }

    /**
     * Checks if this photo is set as the primary profile avatar.
     */
    public boolean isPrimary() {
        return this.displayOrder == 0;
    }

    // --- STATIC INNER BUILDER ---
    public static class Builder {
        private Long id;
        private Long userId;
        private String photoUrl;
        private Integer displayOrder;
        private Instant createdAt;
        private Instant updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder photoUrl(String photoUrl) { this.photoUrl = photoUrl; return this; }
        public Builder displayOrder(Integer displayOrder) { this.displayOrder = displayOrder; return this; }
        public Builder createdAt(Instant createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(Instant updatedAt) { this.updatedAt = updatedAt; return this; }

        public UserPhoto build() {
            if (this.userId == null) {
                throw new IllegalStateException("Domain validation failed: User ID cannot be null");
            }
            if (this.photoUrl == null || this.photoUrl.isBlank()) {
                throw new IllegalStateException("Domain validation failed: Photo URL cannot be empty");
            }
            return new UserPhoto(this);
        }
    }
}

