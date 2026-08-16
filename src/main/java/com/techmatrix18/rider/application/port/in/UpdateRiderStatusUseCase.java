package com.techmatrix18.rider.application.port.in;

import com.techmatrix18.rider.application.command.UpdateRiderStatusCommand;
import com.techmatrix18.rider.domain.model.Rider;

/**
 * Inbound port defining the scenario for changing a rider's season activity status.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface UpdateRiderStatusUseCase {

    // Transitions rider season status after validating ownership invariants.
    Rider execute(UpdateRiderStatusCommand command);
}

