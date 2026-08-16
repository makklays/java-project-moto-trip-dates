package com.techmatrix18.motorcycle.application.service;

import com.techmatrix18.motorcycle.application.command.AddMotorcycleCommand;
import com.techmatrix18.motorcycle.application.port.in.AddMotorcycleUseCase;
import com.techmatrix18.motorcycle.application.port.out.MotorcycleRepository;
import com.techmatrix18.motorcycle.domain.model.Motorcycle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application orchestrator that handles adding a new motorcycle.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

@Service
public class AddMotorcycleService implements AddMotorcycleUseCase {

    private final MotorcycleRepository motorcycleRepositoryPort;

    public AddMotorcycleService(MotorcycleRepository motorcycleRepositoryPort) {
        this.motorcycleRepositoryPort = motorcycleRepositoryPort;
    }

    @Override
    @Transactional
    public Motorcycle execute(AddMotorcycleCommand command) {
        // Сборка доменного объекта через Билдер, который мы доработали
        Motorcycle motorcycle = Motorcycle.builder()
                .riderId(command.riderId())
                .brand(command.brand())
                .model(command.model())
                .bikeType(command.bikeType())
                .engineCapacity(command.engineCapacity())
                .manufactureYear(command.manufactureYear())
                .build();

        // Сохранение через выходной порт инфраструктуры
        return motorcycleRepositoryPort.save(motorcycle);
    }
}

