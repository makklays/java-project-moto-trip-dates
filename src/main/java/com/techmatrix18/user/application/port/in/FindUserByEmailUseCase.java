package com.techmatrix18.user.application.port.in;

import com.techmatrix18.user.application.query.FindUserByEmailQuery;
import com.techmatrix18.user.domain.model.User;
import java.util.Optional;

/**
 * FindUserByEmailUseCase
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public interface FindUserByEmailUseCase {
    Optional<User> execute(FindUserByEmailQuery query); // Передаем Query вместо String
}

