package com.techmatrix18.token.application.port.in;

import com.techmatrix18.token.application.command.RefreshTokenCommand;
import com.techmatrix18.token.domain.model.Token;

/**
 * Inbound port for refreshing an authentication session using a refresh token.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @version 0.0.1
 * @since 17.08.2026
 */

public interface RefreshTokenUseCase {
    Token execute(RefreshTokenCommand command);
}

