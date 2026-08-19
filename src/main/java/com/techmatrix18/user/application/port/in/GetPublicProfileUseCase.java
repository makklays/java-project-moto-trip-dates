package com.techmatrix18.user.application.port.in;

import com.techmatrix18.user.application.query.GetPublicProfileQuery;
import com.techmatrix18.user.domain.model.User;

/**
 * Use case for retrieving a restricted public profile view of a user.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public interface GetPublicProfileUseCase {
    User execute(GetPublicProfileQuery query);
}

