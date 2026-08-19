package com.techmatrix18.user.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * UserJpaRepository
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long userId);
    Optional<UserEntity> findByEmail(String email);
    boolean existsByUserId(Long id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    void deleteById(Long id);

    // 1. Для фильтрации (простой HQL/JPQL пример, для сложных фильтров Александр обычно использует Criteria API или QueryDSL)
    @Query("SELECT u FROM UserEntity u WHERE " +
            "(:search IS NULL OR lower(u.username) LIKE lower(concat('%', :search, '%')) OR lower(u.email) LIKE lower(concat('%', :search, '%'))) " +
            "AND (:statuses IS NULL OR u.datingStatus IN :statuses) " +
            "AND (:gender IS NULL OR u.gender = :gender)")
    Page<UserEntity> findByFiltersWithPage(
            @Param("search") String search,
            @Param("statuses") List<String> statuses,
            @Param("gender") String gender,
            Pageable pageable
    );

    // 2. Для гео-поиска (нативный SQL запрос по формуле гаверсинусов)
    // Предполагается, что в UserEntity есть поля latitude и longitude. Если их нет, этот метод адаптируется под гео-таблицу.
    @Query(value = "SELECT * FROM users u WHERE " +
            "(:datingStatus IS NULL OR u.dating_status = :datingStatus) " +
            "AND (6371 * acos(cos(radians(:lat)) * cos(radians(u.latitude)) * cos(radians(u.longitude) - radians(:lon)) + sin(radians(:lat)) * sin(radians(u.latitude)))) <= :radius",
            nativeQuery = true)
    List<UserEntity> findNearbyUsers(
            @Param("lat") double lat,
            @Param("lon") double lon,
            @Param("radius") double radius,
            @Param("datingStatus") String datingStatus
    );
}

