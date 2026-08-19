package com.techmatrix18.user.application.port.out;

import com.techmatrix18.user.domain.model.User;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

/**
 * Outbound port specifying database persistence operations for User aggregate.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public interface UserRepository {

    User save(User user);

    User saveAndFlush(User user); // Добавляем метод с немедленным выполнением в БД

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    void delete(Long id);

    // Добавляем параметры фильтрации и пагинации
    Page<User> findByFilters(
        String searchTerms,
        List<String> statuses,
        String gender,
        Integer minAge,
        Integer maxAge,
        int page,
        int size
    );

    // Добавляем параметры гео-поиска
    List<User> findNearby(
        double latitude,
        double longitude,
        double radiusInKm,
        String datingStatus
    );
}

