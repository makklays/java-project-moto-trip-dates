package com.techmatrix18.rider.application.port.in;

import com.techmatrix18.rider.application.command.UpdateRiderProfileCommand;
import com.techmatrix18.rider.domain.model.Rider;

/**
 * Inbound port defining the scenario for updating rider attributes.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface UpdateRiderProfileUseCase {

    // Updates rider characteristics after validating data integrity and ownership.
    Rider execute(UpdateRiderProfileCommand command);
}

