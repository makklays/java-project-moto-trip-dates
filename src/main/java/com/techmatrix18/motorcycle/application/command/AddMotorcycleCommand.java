package com.techmatrix18.motorcycle.application.command;

/**
 * Command to register a new motorcycle in rider's garage.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

public record AddMotorcycleCommand(
        Long riderId,
        String brand,
        String model,
        String bikeType,
        Integer engineCapacity,
        Integer manufactureYear
) {}

