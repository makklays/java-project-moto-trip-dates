package com.techmatrix18.motorcycle.application.port.in;

import com.techmatrix18.motorcycle.application.command.UpdateMotorcycleDetailsCommand;
import com.techmatrix18.motorcycle.domain.model.Motorcycle;

/**
 * Inbound port defining the scenario for updating existing motorcycle technical specifications.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

public interface UpdateMotorcycleDetailsUseCase {

    // Updates motorcycle attributes after validating data integrity and ownership invariants.
    Motorcycle execute(UpdateMotorcycleDetailsCommand command);
}

