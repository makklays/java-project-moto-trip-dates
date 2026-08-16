package com.techmatrix18.motorcycle.application.command;

/**
 * Command to update existing motorcycle technical specifications.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

public record UpdateMotorcycleDetailsCommand(
        Long id,
        Long riderId, // Для проверки прав владения в Use Case
        String brand,
        String model,
        String bikeType,
        Integer engineCapacity,
        Integer manufactureYear
) {}

