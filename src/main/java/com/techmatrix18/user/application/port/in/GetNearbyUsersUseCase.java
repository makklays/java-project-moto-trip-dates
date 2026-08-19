package com.techmatrix18.user.application.port.in;

import com.techmatrix18.user.application.query.GetNearbyUsersQuery;
import com.techmatrix18.user.domain.model.User;
import java.util.List;

/**
 * Use case for geospatial search of active users (e.g., nearby drivers).
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

public interface GetNearbyUsersUseCase {
    List<User> execute(GetNearbyUsersQuery query);
}

