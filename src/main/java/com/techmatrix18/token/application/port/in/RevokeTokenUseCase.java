package com.techmatrix18.token.application.port.in;

import com.techmatrix18.token.application.command.RevokeTokenCommand;

/**
 * Inbound port for revoking an active session (Logout operation).
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface RevokeTokenUseCase {
    void execute(RevokeTokenCommand command);
}

