package com.techmatrix18.user.application.port.in;

import com.techmatrix18.user.application.query.SearchUsersQuery;
import com.techmatrix18.user.domain.model.User;
import org.springframework.data.domain.Page; // Или кастомный доменный PageResult

/**
 * Use case for searching and filtering users with pagination.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public interface SearchUsersUseCase {
    Page<User> execute(SearchUsersQuery query);
}

