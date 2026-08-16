package com.techmatrix18.user_photo.application.port.in;

import com.techmatrix18.user_photo.application.command.ChangePhotoOrderCommand;
import com.techmatrix18.user_photo.domain.model.UserPhoto;

/**
 * Inbound port defining the scenario for reordering photos in the gallery.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface ChangePhotoOrderUseCase {

    // Updates photo display sequence priority after confirming ownership invariants.
    UserPhoto execute(ChangePhotoOrderCommand command);
}

