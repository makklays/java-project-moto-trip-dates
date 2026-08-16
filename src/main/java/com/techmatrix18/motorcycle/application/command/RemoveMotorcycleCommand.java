package com.techmatrix18.motorcycle.application.command;

/**
 * Command to delete a motorcycle from rider's profile.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

public record RemoveMotorcycleCommand(
        Long id,
        Long riderId // Защита инварианта: удалить может только владелец
) {}

