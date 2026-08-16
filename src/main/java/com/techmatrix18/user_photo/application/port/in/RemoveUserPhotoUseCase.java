package com.techmatrix18.user_photo.application.port.in;

import com.techmatrix18.user_photo.application.command.RemoveUserPhotoCommand;

/**
 * Inbound port defining the scenario for deleting a photo from the gallery.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface RemoveUserPhotoUseCase {

    // Executes photo deletion process after verifying ownership.
    void execute(RemoveUserPhotoCommand command);
}

