package com.techmatrix18.user.application.service;

import com.techmatrix18.user.application.command.RegisterUserCommand;
import com.techmatrix18.user.application.port.in.RegisterUserUseCase;
import com.techmatrix18.user.application.port.out.UserRepository;
import com.techmatrix18.user.domain.exception.DomainException;
import com.techmatrix18.user.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator implementing the user registration business scenario
 * with strict unique constraint checks and rich domain construction.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User execute(RegisterUserCommand command) {
        // 1. Проверяем инвариант уникальности Email (отсекает 99% обычных повторов)
        if (userRepository.existsByEmail(command.email())) {
            throw new DomainException("User with email '" + command.email() + "' already exists");
        }

        // 2. Проверяем инвариант уникальности Username (если он передан)
        if (command.username() != null && !command.username().isBlank() &&
                userRepository.existsByUsername(command.username())) {
            throw new DomainException("User with username '" + command.username() + "' already exists");
        }

        // 3. Собираем богатую доменную модель через внутренний канонический Builder
        User newUser = User.builder()
                .username(command.username())
                .email(command.email())
                .password(command.password()) // TODO: Обернуть в passwordEncoder.encode() после настройки Spring Security
                .baseRole("USER")
                .mobile(command.mobile())
                .nickname(command.nickname())
                .gender(command.gender())
                .birthDate(command.birthDate())
                .bio(command.bio())
                .datingStatus(command.datingStatus())
                .build();

        // 4. Оборачиваем немедленный сброс в БД в try-catch для отлова Race Condition
        try {
            return userRepository.saveAndFlush(newUser);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // Трансформируем системную ошибку конкурентной записи в понятный доменный Exception
            throw new DomainException("User registration failed due to a conflict. Email or Username might already be taken.", e);
        }
    }
}

