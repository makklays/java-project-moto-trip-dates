package com.techmatrix18.user.application.service;

import com.techmatrix18.user.application.port.in.CheckUserIdentityAvailabilityUseCase;
import com.techmatrix18.user.application.query.CheckUserIdentityAvailabilityQuery;
import com.techmatrix18.user.application.port.out.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator providing lightweight real-time uniqueness validation.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Service
public class CheckUserIdentityAvailabilityService implements CheckUserIdentityAvailabilityUseCase {

    private final UserRepository userRepository;

    public CheckUserIdentityAvailabilityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AvailabilityResult execute(CheckUserIdentityAvailabilityQuery query) {
        boolean emailExists = query.email() != null && userRepository.existsByEmail(query.email());
        boolean usernameExists = query.username() != null && userRepository.existsByUsername(query.username());

        // Доступен (Available), если НЕ существует (not exists) в системе
        return new AvailabilityResult(!emailExists, !usernameExists);
    }
}

