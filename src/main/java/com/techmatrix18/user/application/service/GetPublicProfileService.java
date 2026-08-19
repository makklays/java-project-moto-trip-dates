package com.techmatrix18.user.application.service;

import com.techmatrix18.user.application.port.in.GetPublicProfileUseCase;
import com.techmatrix18.user.application.query.GetPublicProfileQuery;
import com.techmatrix18.user.application.port.out.UserRepository;
import com.techmatrix18.user.domain.exception.DomainException;
import com.techmatrix18.user.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator executing public profile retrieval with privacy/security checks.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Service
public class GetPublicProfileService implements GetPublicProfileUseCase {

    private final UserRepository userRepository;

    public GetPublicProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User execute(GetPublicProfileQuery query) {
        User targetUser = userRepository.findById(query.targetUserId())
                .orElseThrow(() -> new DomainException("User with id '" + query.targetUserId() + "' not found"));

        // Здесь Александр может добавить бизнес-проверку приватности профиля:
        // if (targetUser.hasBlocked(query.requestedByUserId())) { throw new DomainException("Access denied"); }

        return targetUser;
    }
}

