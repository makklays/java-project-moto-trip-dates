package com.techmatrix18.rider.application.port.in;

import com.techmatrix18.rider.application.command.CreateRiderProfileCommand;
import com.techmatrix18.rider.domain.model.Rider;

/**
 * Inbound port defining the scenario for initializing a new rider profile.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface CreateRiderProfileUseCase {

    // Creates and registers a new rider profile linked to a user.
    Rider execute(CreateRiderProfileCommand command);
}

