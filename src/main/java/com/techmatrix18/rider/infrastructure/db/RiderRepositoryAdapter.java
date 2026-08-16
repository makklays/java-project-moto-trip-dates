package com.techmatrix18.rider.infrastructure.db;

import com.techmatrix18.rider.application.port.out.RiderRepository;
import com.techmatrix18.rider.domain.model.Rider;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Infrastructure adapter that implements RiderRepositoryPort.
 * Translates database entities to clean rich domain models.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Component
class RiderRepositoryAdapter implements RiderRepository {

    private final RiderJpaRepository jpaRepository;

    public RiderRepositoryAdapter(RiderJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Rider save(Rider rider) {
        // Конвертируем чистый Домен в JPA-Entity для Hibernate
        RiderEntity entity = toEntity(rider);
        RiderEntity savedEntity = jpaRepository.save(entity);

        // Возвращаем обратно чистый Домен, собранный через Builder
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Rider> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Rider> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId).map(this::toDomain);
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return jpaRepository.existsByUserId(userId);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    // --- MAPPING HELPERS ---
    private Rider toDomain(RiderEntity entity) {
        return Rider.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .nickname(entity.getNickname())
                .ridingSinceYear(entity.getRidingSinceYear())
                .drivingStyle(entity.getDrivingStyle())
                .riderType(entity.getRiderType())
                .hasHelmetForPassenger(entity.isHasHelmetForPassenger())
                .bloodType(entity.getBloodType())
                .bio(entity.getBio())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private RiderEntity toEntity(Rider rider) {
        RiderEntity entity = new RiderEntity();
        entity.setId(rider.getId());
        entity.setUserId(rider.getUserId());
        entity.setNickname(rider.getNickname());
        entity.setRidingSinceYear(rider.getRidingSinceYear());
        entity.setDrivingStyle(rider.getDrivingStyle());
        entity.setRiderType(rider.getRiderType());
        entity.setHasHelmetForPassenger(rider.isHasHelmetForPassenger());
        entity.setBloodType(rider.getBloodType());
        entity.setBio(rider.getBio());
        entity.setStatus(rider.getStatus());
        entity.setCreatedAt(rider.getCreatedAt());
        entity.setUpdatedAt(rider.getUpdatedAt());
        return entity;
    }
}

