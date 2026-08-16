package com.techmatrix18.rider.application.service;

import com.techmatrix18.rider.application.command.UpdateRiderProfileCommand;
import com.techmatrix18.rider.application.port.in.UpdateRiderProfileUseCase;
import com.techmatrix18.rider.application.port.out.RiderRepository;
import com.techmatrix18.rider.domain.model.Rider;
import com.techmatrix18.user.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator that updates rider profile details with ownership verification.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class UpdateRiderProfileService implements UpdateRiderProfileUseCase {

    private final RiderRepository riderRepository;

    public UpdateRiderProfileService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @Override
    @Transactional
    public Rider execute(UpdateRiderProfileCommand command) {
        // 1. Извлекаем существующий профиль
        Rider rider = riderRepository.findById(command.id())
                .orElseThrow(() -> new DomainException("Rider profile not found with ID: " + command.id()));

        // 2. Проверяем инвариант безопасности: редактировать профиль может только его владелец
        if (!rider.getUserId().equals(command.userId())) {
            throw new DomainException("Access denied: You do not have permission to modify this profile");
        }

        // 3. Мутируем состояние Rich-модели через инкапсулированный метод поведения
        rider.updateProfile(
                command.nickname(),
                command.ridingSinceYear(),
                command.drivingStyle(),
                command.riderType(),
                command.hasHelmetForPassenger(),
                command.bloodType(),
                command.bio()
        );

        return riderRepository.save(rider);
    }
}

