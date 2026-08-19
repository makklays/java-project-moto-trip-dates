package com.techmatrix18.user.application.port.in;

import com.techmatrix18.user.application.query.GetUserQuery;
import com.techmatrix18.user.domain.model.User;

/**
 * GetUserUseCase
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public interface GetUserUseCase {
    User execute(GetUserQuery query);
}

