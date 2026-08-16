package com.techmatrix18.motorcycle.application.service;

import com.techmatrix18.motorcycle.application.command.RemoveMotorcycleCommand;
import com.techmatrix18.motorcycle.application.port.in.RemoveMotorcycleUseCase;
import com.techmatrix18.motorcycle.application.port.out.MotorcycleRepository;
import com.techmatrix18.motorcycle.domain.model.Motorcycle;
import com.techmatrix18.user.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service orchestrator implementing the motorcycle removal scenario
 * with ownership invariant enforcement.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

@Service
public class RemoveMotorcycleService implements RemoveMotorcycleUseCase {

    private final MotorcycleRepository motorcycleRepositoryPort;

    public RemoveMotorcycleService(MotorcycleRepository motorcycleRepositoryPort) {
        this.motorcycleRepositoryPort = motorcycleRepositoryPort;
    }

    @Override
    @Transactional
    public void execute(RemoveMotorcycleCommand command) {
        // 1. Извлекаем доменную модель мотоцикла из репозитория
        Motorcycle motorcycle = motorcycleRepositoryPort.findById(command.id())
                .orElseThrow(() -> new DomainException("Motorcycle with ID " + command.id() + " not found"));

        // 2. Проверяем инвариант безопасности: ID запрашивающего должен совпадать с владельцем байка
        if (!motorcycle.getRiderId().equals(command.riderId())) {
            throw new DomainException("Access denied: You do not have permission to delete this motorcycle");
        }

        // 3. Вызываем удаление через выходной порт
        motorcycleRepositoryPort.delete(command.id());
    }
}

