package com.techmatrix18.user.application.service;

import com.techmatrix18.user.application.port.in.GetNearbyUsersUseCase;
import com.techmatrix18.user.application.query.GetNearbyUsersQuery;
import com.techmatrix18.user.application.port.out.UserRepository;
import com.techmatrix18.user.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Service orchestrator executing geospatial radius search for active domain aggregates.
 */
@Service
public class GetNearbyUsersService implements GetNearbyUsersUseCase {

    private final UserRepository userRepository;

    public GetNearbyUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> execute(GetNearbyUsersQuery query) {
        // Передаем координаты и радиус в выходной порт
        return userRepository.findNearby(
                query.latitude(),
                query.longitude(),
                query.radiusInKm(),
                query.datingStatus()
        );
    }
}

