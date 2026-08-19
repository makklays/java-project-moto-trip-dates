package com.techmatrix18.user.application.service;

import com.techmatrix18.user.application.port.in.FindUserByEmailUseCase;
import com.techmatrix18.user.application.query.FindUserByEmailQuery;
import com.techmatrix18.user.application.port.out.UserRepository;
import com.techmatrix18.user.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * FindUserByEmailService
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 19.08.2026
 */

@Service
public class FindUserByEmailService implements FindUserByEmailUseCase {

    private final UserRepository userRepository;

    public FindUserByEmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> execute(FindUserByEmailQuery query) {
        if (query.email() == null || query.email().isBlank()) {
            return Optional.empty();
        }
        return userRepository.findByEmail(query.email());
    }
}

