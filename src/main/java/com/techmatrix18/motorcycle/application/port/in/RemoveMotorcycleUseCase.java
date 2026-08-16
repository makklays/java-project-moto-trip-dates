package com.techmatrix18.motorcycle.application.port.in;

import com.techmatrix18.motorcycle.application.command.RemoveMotorcycleCommand;

/**
 * Inbound port defining the scenario for deleting a motorcycle from a rider's profile.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

public interface RemoveMotorcycleUseCase {

    // Executes motorcycle removal after verifying ownership invariants.
    void execute(RemoveMotorcycleCommand command);
}

