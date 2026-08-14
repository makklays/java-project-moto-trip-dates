package com.techmatrix18.domain.model;

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

    /**
     * All-args constructor for initializing domain object from persistence layer or factory.
     */
    public UserPhoto(Long id, Long userId, String photoUrl, Integer displayOrder,
                     Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.photoUrl = photoUrl;
        this.displayOrder = displayOrder != null ? displayOrder : 0;
        this.createdAt = createdAt != null ? createdAt : Instant.now();
        this.updatedAt = updatedAt != null ? updatedAt : Instant.now();
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
}

