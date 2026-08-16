package com.techmatrix18.rider.application.service;

import com.techmatrix18.rider.application.command.CreateRiderProfileCommand;
import com.techmatrix18.rider.application.port.in.CreateRiderProfileUseCase;
import com.techmatrix18.rider.application.port.out.RiderRepository;
import com.techmatrix18.rider.domain.model.Rider;
import com.techmatrix18.user.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator that handles creation of a new rider profile.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class CreateRiderProfileService implements CreateRiderProfileUseCase {

    private final RiderRepository riderRepository;

    public CreateRiderProfileService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @Override
    @Transactional
    public Rider execute(CreateRiderProfileCommand command) {
        // Защита инварианта: у одного пользователя может быть строго один профиль райдера
        if (riderRepository.existsByUserId(command.userId())) {
            throw new DomainException("Rider profile already exists for user ID: " + command.userId());
        }

        // Сборка доменного агрегата через Builder
        Rider rider = Rider.builder()
                .userId(command.userId())
                .nickname(command.nickname())
                .ridingSinceYear(command.ridingSinceYear())
                .drivingStyle(command.drivingStyle())
                .riderType(command.riderType())
                .hasHelmetForPassenger(command.hasHelmetForPassenger())
                .bloodType(command.bloodType())
                .bio(command.bio())
                .status(command.status())
                .build();

        return riderRepository.save(rider);
    }
}

