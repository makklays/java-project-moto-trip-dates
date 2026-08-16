package com.techmatrix18.rider.application.port.out;

import com.techmatrix18.rider.domain.model.Rider;
import java.util.Optional;

/**
 * Outbound port specifying database persistence operations for Rider aggregate.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface RiderRepository {

    /**
     * Saves or updates a rider profile aggregate.
     */
    Rider save(Rider rider);

    /**
     * Finds rider profile details by its primary key.
     */
    Optional<Rider> findById(Long id);

    /**
     * Finds rider profile by the unique linked user account ID.
     */
    Optional<Rider> findByUserId(Long userId);

    /**
     * Checks if a rider profile already exists for a specific user.
     * Use to protect the business invariant: one user can have only one rider profile.
     */
    boolean existsByUserId(Long userId);

    /**
     * Permanently deletes a rider profile metadata record by its ID.
     */
    void delete(Long id);
}

