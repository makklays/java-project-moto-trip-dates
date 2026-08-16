package com.techmatrix18.rider.application.service;

import com.techmatrix18.rider.application.command.UpdateRiderStatusCommand;
import com.techmatrix18.rider.application.port.in.UpdateRiderStatusUseCase;
import com.techmatrix18.rider.application.port.out.RiderRepository;
import com.techmatrix18.rider.domain.model.Rider;
import com.techmatrix18.user.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator that changes rider season activity status.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

@Service
public class UpdateRiderStatusService implements UpdateRiderStatusUseCase {

    private final RiderRepository riderRepository;

    public UpdateRiderStatusService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @Override
    @Transactional
    public Rider execute(UpdateRiderStatusCommand command) {
        // 1. Извлекаем профиль
        Rider rider = riderRepository.findById(command.id())
                .orElseThrow(() -> new DomainException("Rider profile not found with ID: " + command.id()));

        // 2. Проверяем инвариант безопасности: менять статус может только владелец
        if (!rider.getUserId().equals(command.userId())) {
            throw new DomainException("Access denied: You do not have permission to modify this status");
        }

        // 3. Вызываем доменный метод смены статуса (там же проверяется валидность и ставится updatedAt)
        rider.updateStatus(command.status());

        return riderRepository.save(rider);
    }
}

