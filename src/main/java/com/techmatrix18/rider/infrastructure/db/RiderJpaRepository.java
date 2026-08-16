package com.techmatrix18.rider.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * RiderJpaRepository
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Repository
interface RiderJpaRepository extends JpaRepository<RiderEntity, Long> {
    Optional<RiderEntity> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}

