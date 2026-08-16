package com.techmatrix18.motorcycle.application.service;

import com.techmatrix18.motorcycle.application.command.UpdateMotorcycleDetailsCommand;
import com.techmatrix18.motorcycle.application.port.in.UpdateMotorcycleDetailsUseCase;
import com.techmatrix18.motorcycle.application.port.out.MotorcycleRepository;
import com.techmatrix18.motorcycle.domain.model.Motorcycle;
import com.techmatrix18.user.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator implementing the motorcycle profile update scenario
 * with security validation and rich domain mutation.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

@Service
public class UpdateMotorcycleDetailsService implements UpdateMotorcycleDetailsUseCase {

    private final MotorcycleRepository motorcycleRepositoryPort;

    public UpdateMotorcycleDetailsService(MotorcycleRepository motorcycleRepositoryPort) {
        this.motorcycleRepositoryPort = motorcycleRepositoryPort;
    }

    @Override
    @Transactional
    public Motorcycle execute(UpdateMotorcycleDetailsCommand command) {
        // 1. Находим мотоцикл в базе данных через выходной порт
        Motorcycle motorcycle = motorcycleRepositoryPort.findById(command.id())
                .orElseThrow(() -> new DomainException("Motorcycle with ID " + command.id() + " not found"));

        // 2. Проверяем инвариант безопасности (владение мотоциклом)
        if (!motorcycle.getRiderId().equals(command.riderId())) {
            throw new DomainException("Access denied: You do not have permission to modify this motorcycle");
        }

        // 3. Мутируем состояние объекта внутри Rich Domain модели (там же обновляется метка updatedAt)
        motorcycle.updateDetails(
                command.brand(),
                command.model(),
                command.bikeType(),
                command.engineCapacity(),
                command.manufactureYear()
        );

        // 4. Сохраняем обновленный доменный объект
        return motorcycleRepositoryPort.save(motorcycle);
    }
}

