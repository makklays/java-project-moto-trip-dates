package com.techmatrix18.motorcycle.application.port.in;

import com.techmatrix18.motorcycle.application.command.AddMotorcycleCommand;
import com.techmatrix18.motorcycle.domain.model.Motorcycle;

/**
 * Inbound port for adding a motorcycle to the rider's profile.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

public interface AddMotorcycleUseCase {
    Motorcycle execute(AddMotorcycleCommand command);
}

