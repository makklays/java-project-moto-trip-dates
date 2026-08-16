package com.techmatrix18.token.application.port.in;

import com.techmatrix18.token.application.command.InitiatePasswordResetCommand;
import com.techmatrix18.token.domain.model.Token;

/**
 * Inbound port for generating and linking a password reset token.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface InitiatePasswordResetUseCase {
    Token execute(InitiatePasswordResetCommand command);
}

