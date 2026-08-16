package com.techmatrix18.user_photo.application.port.out;

import com.techmatrix18.user_photo.domain.model.UserPhoto;
import java.util.List;
import java.util.Optional;

/**
 * Outbound port specifying database persistence operations for UserPhoto aggregate.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface UserPhotoRepository {

    // Saves or updates user photo metadata.
    UserPhoto save(UserPhoto userPhoto);

    // Finds photo details by its primary key.
    Optional<UserPhoto> findById(Long id);

    // Retrieves all photos for a specific user ordered by display priority.
    List<UserPhoto> findByUserIdOrderByDisplayOrderAsc(Long userId);

    // Permanently deletes a photo metadata record by its ID.
    void delete(Long id);

    // Counts how many photos a user has already uploaded.
    // Useful for business rules (e.g., maximum 10 photos in a profile).
    long countByUserId(Long userId);
}

