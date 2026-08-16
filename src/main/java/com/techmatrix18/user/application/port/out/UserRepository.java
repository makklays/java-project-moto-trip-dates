package com.techmatrix18.user.application.port.out;

import com.techmatrix18.user.domain.model.User;
import java.util.Optional;

/**
 * Outbound port specifying database persistence operations for User aggregate.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    void delete(Long id);
}

