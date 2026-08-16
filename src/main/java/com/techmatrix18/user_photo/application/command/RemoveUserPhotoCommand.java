package com.techmatrix18.user_photo.application.command;

/**
 * Command to permanently delete a photo from the user's gallery.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record RemoveUserPhotoCommand(
        Long id,
        Long userId // Защита инварианта: удалить может только владелец
) {}

