package com.techmatrix18.user.application.port.in;

import com.techmatrix18.user.application.command.RegisterUserCommand;
import com.techmatrix18.user.domain.model.User;

/**
 * Inbound port defining the user registration business scenario.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 16.08.2026
 */

public interface RegisterUserUseCase {

    // Executes the user registration process.
    User execute(RegisterUserCommand command);
}

