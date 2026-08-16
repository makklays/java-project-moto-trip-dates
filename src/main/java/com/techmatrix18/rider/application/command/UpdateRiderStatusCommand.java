package com.techmatrix18.rider.application.command;

/**
 * Command to transition rider season activity status.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record UpdateRiderStatusCommand(
        Long id,
        Long userId, // Защита инварианта: изменить статус может только владелец
        String status
) {}

