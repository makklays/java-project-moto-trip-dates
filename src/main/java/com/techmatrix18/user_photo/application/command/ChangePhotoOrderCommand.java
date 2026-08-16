package com.techmatrix18.user_photo.application.command;

/**
 * Command to reorder photo position in the profile gallery.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record ChangePhotoOrderCommand(
        Long id,
        Long userId, // Для проверки инварианта владения в сервисе
        int newOrder
) {}

