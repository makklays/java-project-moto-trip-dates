package com.techmatrix18.user_photo.application.command;


/**
 * Command to upload and register a new photo in the user's gallery.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public record UploadUserPhotoCommand(
        Long userId,
        String photoUrl,
        Integer displayOrder
) {}

